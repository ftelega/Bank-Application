package ft.springprojects.bankapp.controller;

import ft.springprojects.bankapp.dto.AddressExceptionDTO;
import ft.springprojects.bankapp.dto.UserExceptionDTO;
import ft.springprojects.bankapp.model.AddressException;
import ft.springprojects.bankapp.model.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeFormatter;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerHandler {

    private final DateTimeFormatter dateTimeFormatter;

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<UserExceptionDTO> handleUserException(UserException e){
        return ResponseEntity.status(e.getStatus()).body(new UserExceptionDTO(
               e.getStatus().value(),
               dateTimeFormatter.format(e.getTime()),
               e.getMessage()
        ));
    }

    @ExceptionHandler(value = AddressException.class)
    public ResponseEntity<AddressExceptionDTO> handleAddressException(AddressException e){
        return ResponseEntity.status(e.getStatus()).body(new AddressExceptionDTO(
                e.getStatus().value(),
                dateTimeFormatter.format(e.getTime()),
                e.getMessage()
        ));
    }
}
