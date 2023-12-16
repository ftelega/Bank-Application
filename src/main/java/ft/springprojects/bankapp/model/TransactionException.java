package ft.springprojects.bankapp.model;

import ft.springprojects.bankapp.enums.TransactionExceptions;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class TransactionException extends ApiException{

    public TransactionException(TransactionExceptions message, HttpStatus status, LocalDateTime time) {
        super(message.name(), status, time);
    }
}
