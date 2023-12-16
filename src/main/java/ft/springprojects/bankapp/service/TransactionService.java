package ft.springprojects.bankapp.service;

import java.math.BigDecimal;

public interface TransactionService {
    void deposit(BigDecimal amount);
    void transfer(Integer receiverId);

}
