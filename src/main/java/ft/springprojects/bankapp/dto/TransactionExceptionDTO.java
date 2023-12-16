package ft.springprojects.bankapp.dto;

public class TransactionExceptionDTO extends ApiExceptionDTO{
    public TransactionExceptionDTO(int status, String time, String message) {
        super(status, time, message);
    }
}
