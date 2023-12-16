package ft.springprojects.bankapp.validation;

import java.math.BigDecimal;

public interface TransactionValidator {
    void validateTransfer(BigDecimal amount, String principal, Long receiverId);
}
