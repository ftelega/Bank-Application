package ft.springprojects.bankapp.model;

import ft.springprojects.bankapp.enums.UserExceptions;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class UserException extends ApiException {

    public UserException(UserExceptions message, HttpStatus status, LocalDateTime time) {
        super(message.name(), status, time);
    }
}
