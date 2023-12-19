package ft.springprojects.bankapp;

import ft.springprojects.bankapp.model.User;
import ft.springprojects.bankapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import static ft.springprojects.bankapp.TestUtil.*;

import java.util.Optional;

public class BaseSecurityTest extends BaseTest{

    public BaseSecurityTest(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        persistTestSecurityUser(userRepository, passwordEncoder);
    }

    private void persistTestSecurityUser(UserRepository userRepository, PasswordEncoder passwordEncoder){
        Optional<User> testSecurityUser = userRepository.findByEmail(TEST_SECURITY_PRINCIPAL);
        if(testSecurityUser.isEmpty()){
            userRepository.save(User.builder().email(TEST_SECURITY_PRINCIPAL).password(passwordEncoder.encode(TEST_SECURITY_CREDENTIALS)).build());
        }
    }
}
