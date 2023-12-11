package ft.springprojects.bankapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ft.springprojects.bankapp.enums.UserExceptions;
import ft.springprojects.bankapp.model.UserException;
import ft.springprojects.bankapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ft.springprojects.bankapp.TestUtil.*;
import static org.mockito.BDDMockito.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureJsonTesters
class UserControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private DateTimeFormatter dateTimeFormatter;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void whenCreatingUser_thenStatusOk() throws Exception {

        ResultActions res = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CORRECT_USERDTO))
        );

        res.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenServiceThrowsException_whenCreatingUser_thenStatusBadRequest() throws Exception {
        doThrow(new UserException(UserExceptions.USERNAME_EXISTS, HttpStatus.BAD_REQUEST, LocalDateTime.now())).when(userService).createUser(any());

        ResultActions res = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CORRECT_USERDTO))
        );

        res.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}