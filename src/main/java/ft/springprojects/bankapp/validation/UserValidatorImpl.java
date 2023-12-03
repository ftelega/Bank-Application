package ft.springprojects.bankapp.validation;

import ft.springprojects.bankapp.dto.UserDTO;
import ft.springprojects.bankapp.enums.UserExceptions;
import ft.springprojects.bankapp.model.UserException;
import ft.springprojects.bankapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserValidatorImpl implements UserValidator {

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{4,20}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]{2,10}@[a-zA-Z0-9.-]{2,10}\\.[a-zA-Z]{2,5}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()-+=])[a-zA-Z0-9!@#$%^&*()-+=]{8,20}$");
    private final UserRepository userRepository;

    @Override
    public void validateUser(UserDTO userDTO) {
        validateUsername(userDTO.username());
        validateEmail(userDTO.email());
        validatePassword(userDTO.password());
        usernameExists(userDTO.username());
        emailExists(userDTO.email());
    }

    public void validateUsername(String username){
        if(username == null || !USERNAME_PATTERN.matcher(username).matches()) throw new UserException(UserExceptions.INVALID_USERNAME, HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }

    public void validateEmail(String email){
        if(email == null || !EMAIL_PATTERN.matcher(email).matches()) throw new UserException(UserExceptions.INVALID_EMAIL, HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }

    public void validatePassword(String password){
        if(password == null || !PASSWORD_PATTERN.matcher(password).matches()) throw new UserException(UserExceptions.INVALID_PASSWORD, HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }

    public void usernameExists(String username){
        if(userRepository.findByUsername(username).isPresent()) throw new UserException(UserExceptions.USERNAME_EXISTS, HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }

    public void emailExists(String email){
        if(userRepository.findByEmail(email).isPresent()) throw new UserException(UserExceptions.EMAIL_EXISTS, HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }
}
