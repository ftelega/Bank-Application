package ft.springprojects.bankapp.model;

import ft.springprojects.bankapp.enums.AddressExceptions;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class AddressException extends ApiException {

    public AddressException(AddressExceptions message, HttpStatus status, LocalDateTime time) {
        super(message.name(), status, time);
    }
}
