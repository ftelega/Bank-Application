package ft.springprojects.bankapp;

import ft.springprojects.bankapp.model.User;
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

import java.math.BigDecimal;

import static ft.springprojects.bankapp.TestUtil.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransactionFlowTest extends BaseSecurityTest{

    private final MockMvc mockMvc;
    private final UserRepository userRepository;

    @Autowired
    public TransactionFlowTest(UserRepository userRepository, PasswordEncoder passwordEncoder, MockMvc mockMvc) {
        super(userRepository, passwordEncoder);
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
    }

    @Test
    public void givenExistingUserAndCorrectAmountWithToken_whenTransferring_thenStatusOk() throws Exception{
        User user = userRepository.save(User.builder().email("awd").build());

        ResultActions res = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/v1/transaction/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .param("receiver", String.valueOf(user.getId()))
                .param("amount", String.valueOf(BigDecimal.TEN))
                .header(JWT_HEADER, generateJwt(TEST_SECURITY_PRINCIPAL, null))
        );

        res.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenExistingUserAndCorrectAmountWithBasic_whenTransferring_thenStatusOk() throws Exception{
        User user = userRepository.save(User.builder().email("awd").build());

        ResultActions res = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/v1/transaction/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .param("receiver", String.valueOf(user.getId()))
                .param("amount", String.valueOf(BigDecimal.TEN))
                .with(SecurityMockMvcRequestPostProcessors.httpBasic(TEST_SECURITY_PRINCIPAL, TEST_SECURITY_CREDENTIALS))
        );

        res.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
