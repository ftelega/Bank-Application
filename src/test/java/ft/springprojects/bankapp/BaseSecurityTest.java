package ft.springprojects.bankapp;

import ft.springprojects.bankapp.model.User;
import ft.springprojects.bankapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.crypto.password.PasswordEncoder;

import static ft.springprojects.bankapp.TestUtil.*;

public class BaseSecurityTest extends BaseTest{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public BaseSecurityTest(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @BeforeEach
    public void setup(){
        userRepository.deleteAll();
        userRepository.save(User.builder().email(TEST_SECURITY_PRINCIPAL).password(passwordEncoder.encode(TEST_SECURITY_CREDENTIALS)).build());
    }
}
