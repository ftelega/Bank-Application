package ft.springprojects.bankapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import ft.springprojects.bankapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static ft.springprojects.bankapp.TestUtil.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserFlowTest extends BaseSecurityTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserFlowTest(MockMvc mockMvc, ObjectMapper objectMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(userRepository, passwordEncoder);
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
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
    public void givenCorrectHttpBasicCredentials_whenLoggingIn_thenStatusOk() throws Exception {

        ResultActions res = mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/api/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic(TEST_SECURITY_PRINCIPAL, TEST_SECURITY_CREDENTIALS))
        );

        res.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenIncorrectHttpBasicCredentials_whenLoggingIn_thenStatusUnauthorized() throws Exception {

        ResultActions res = mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/api/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("awd", "awd"))
        );

        res.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
