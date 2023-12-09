package ft.springprojects.bankapp.validation;

import ft.springprojects.bankapp.dto.UserDTO;
import ft.springprojects.bankapp.model.User;
import ft.springprojects.bankapp.model.UserException;
import ft.springprojects.bankapp.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static ft.springprojects.bankapp.TestUtil.*;

class UserValidatorTest {

    private final UserValidator userValidator;
    private final UserRepository userRepository;

    public UserValidatorTest() {
        this.userRepository = mock(UserRepository.class);
        this.userValidator = new UserValidatorImpl(userRepository);
    }

    @Test
    public void givenValidUser_whenValidateUser_thenNoException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertDoesNotThrow(() -> {
            userValidator.validateUser(CORRECT_USERDTO);
        });
    }

    @Test
    public void givenInvalidUsername_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(EMPTY_USERNAME_USERDTO);
        });
    }

    @Test
    public void givenInvalidUsername2_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(NULL_USERNAME_USERDTO);
        });
    }

    @Test
    public void givenInvalidEmail_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(EMPTY_EMAIL_USERDTO);
        });
    }

    @Test
    public void givenInvalidEmail2_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(NULL_EMAIL_USERDTO);
        });
    }

    @Test
    public void givenInvalidPassword_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(EMPTY_PASSWORD_USERDTO);
        });
    }

    @Test
    public void givenInvalidPassword2_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(NULL_PASSWORD_USERDTO);
        });
    }

    @Test
    public void givenUsernameExists_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.of(new User()));
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(CORRECT_USERDTO);
        });
    }

    @Test
    public void givenEmailExists_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.of(new User()));

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(CORRECT_USERDTO);
        });
    }
}