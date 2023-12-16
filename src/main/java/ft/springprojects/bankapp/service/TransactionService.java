package ft.springprojects.bankapp.service;

import java.math.BigDecimal;

public interface TransactionService {
    void transfer(BigDecimal amount, Long receiverId);
}
