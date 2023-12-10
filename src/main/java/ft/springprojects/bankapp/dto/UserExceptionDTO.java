package ft.springprojects.bankapp.dto;

public class UserExceptionDTO extends ApiExceptionDTO {

    public UserExceptionDTO(int status, String time, String message) {
        super(status, time, message);
    }
}
