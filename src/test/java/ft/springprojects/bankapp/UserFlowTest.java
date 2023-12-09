package ft.springprojects.bankapp;

import ft.springprojects.bankapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static ft.springprojects.bankapp.TestUtil.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserFlowTest extends BaseTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserFlowTest(MockMvc mockMvc, ObjectMapper objectMapper, UserRepository userRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void setup(){
        userRepository.deleteAll();
    }

    @Test
    public void givenCorrectUser_whenCreateUser_thenStatusOk() throws Exception {

        ResultActions res = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CORRECT_USERDTO))
        );

        res.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenIncorrectUsername_whenCreateUser_thenStatusBadRequest() throws Exception {

        ResultActions res = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(EMPTY_USERNAME_USERDTO))
        );

        res.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
