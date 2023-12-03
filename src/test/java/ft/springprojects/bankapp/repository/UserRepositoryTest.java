package ft.springprojects.bankapp.repository;

import ft.springprojects.bankapp.BaseTest;
import ft.springprojects.bankapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ft.springprojects.bankapp.TestUtil.*;


@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest extends BaseTest {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void setup(){
        userRepository.deleteAll();
    }

    @Test
    public void givenUserExists_whenFindByUsername_thenOptionalPresent(){
        userRepository.save(User.builder().username(CORRECT_USERNAME).build());

        Optional<User> user = userRepository.findByUsername(CORRECT_USERNAME);

        assertTrue(user.isPresent());
    }

    @Test
    public void givenUserExists_whenFindByEmail_thenOptionalPresent(){
        userRepository.save(User.builder().email(CORRECT_EMAIL).build());

        Optional<User> user = userRepository.findByEmail(CORRECT_EMAIL);

        assertTrue(user.isPresent());
    }

    @Test
    public void givenUserNotExists_whenFindByUsername_thenOptionalEmpty(){
        Optional<User> user = userRepository.findByUsername(CORRECT_USERNAME);

        assertTrue(user.isEmpty());
    }

    @Test
    public void givenUserNotExists_whenFindByEmail_thenOptionalEmpty(){
        Optional<User> user = userRepository.findByEmail(CORRECT_EMAIL);

        assertTrue(user.isEmpty());
    }
}