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
            userValidator.validateUser(new UserDTO(CORRECT_USERNAME, CORRECT_EMAIL, CORRECT_PASSWORD, null, null));
        });
    }

    @Test
    public void givenInvalidUsername_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(new UserDTO(null, CORRECT_EMAIL, CORRECT_PASSWORD, null, null));
        });
    }

    @Test
    public void givenInvalidUsername2_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(new UserDTO("", CORRECT_EMAIL, CORRECT_PASSWORD, null, null));
        });
    }

    @Test
    public void givenInvalidUsername3_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(new UserDTO("***", CORRECT_EMAIL, CORRECT_PASSWORD, null, null));
        });
    }

    @Test
    public void givenInvalidEmail_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(new UserDTO(CORRECT_USERNAME, null, CORRECT_PASSWORD, null, null));
        });
    }

    @Test
    public void givenInvalidEmail2_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(new UserDTO(CORRECT_USERNAME, "", CORRECT_PASSWORD, null, null));
        });
    }

    @Test
    public void givenInvalidEmail3_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(new UserDTO(CORRECT_USERNAME, "***", CORRECT_PASSWORD, null, null));
        });
    }

    @Test
    public void givenInvalidPassword_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(new UserDTO(CORRECT_USERNAME, CORRECT_EMAIL, null, null, null));
        });
    }

    @Test
    public void givenInvalidPassword2_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(new UserDTO(CORRECT_USERNAME, CORRECT_EMAIL, "", null, null));
        });
    }

    @Test
    public void givenInvalidPassword3_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(new UserDTO(CORRECT_USERNAME, CORRECT_EMAIL, "***", null, null));
        });
    }

    @Test
    public void givenUsernameExists_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.of(new User()));
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(new UserDTO(CORRECT_USERNAME, CORRECT_EMAIL, CORRECT_EMAIL, null, null));
        });
    }

    @Test
    public void givenEmailExists_whenValidateUser_thenException(){
        given(userRepository.findByUsername(any())).willReturn(Optional.empty());
        given(userRepository.findByEmail(any())).willReturn(Optional.of(new User()));

        assertThrows(UserException.class, () -> {
            userValidator.validateUser(new UserDTO(CORRECT_USERNAME, CORRECT_EMAIL, CORRECT_EMAIL, null, null));
        });
    }
}