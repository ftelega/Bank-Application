package ft.springprojects.bankapp.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public abstract class ApiException extends RuntimeException {

    private final HttpStatus status;
    private final LocalDateTime time;

    public ApiException(String message, HttpStatus status, LocalDateTime time) {
        super(message);
        this.status = status;
        this.time = time;
    }
}
