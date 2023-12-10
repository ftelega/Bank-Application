package ft.springprojects.bankapp.dto;

public class AddressExceptionDTO extends ApiExceptionDTO {

    public AddressExceptionDTO(int status, String time, String message) {
        super(status, time, message);
    }
}
