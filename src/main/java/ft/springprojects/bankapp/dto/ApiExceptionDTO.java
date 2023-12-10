package ft.springprojects.bankapp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApiExceptionDTO {
        private final int status;
        private final String time;
        private final String message;
}
