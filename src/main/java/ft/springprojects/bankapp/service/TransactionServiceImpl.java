package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.model.Transaction;
import ft.springprojects.bankapp.model.User;
import ft.springprojects.bankapp.repository.TransactionRepository;
import ft.springprojects.bankapp.validation.TransactionValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final TransactionValidator transactionValidator;
    private final UserTransactionService userTransactionService;

    @Override
    @Transactional
    public void transfer(BigDecimal amount, Long receiverId) {
        String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        transactionValidator.validateTransfer(amount, principal, receiverId);
        User sender = userTransactionService.getTransferSender(principal);
        User receiver = userTransactionService.getTransferReceiver(receiverId);
        sender.setBalance(sender.getBalance().subtract(amount));
        transactionRepository.save(Transaction.builder()
                .amount(amount)
                .fromUser(sender)
                .toUser(receiver)
                .build()
        );
    }
}
