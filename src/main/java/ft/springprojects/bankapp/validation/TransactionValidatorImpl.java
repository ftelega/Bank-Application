package ft.springprojects.bankapp.validation;

import ft.springprojects.bankapp.enums.TransactionExceptions;
import ft.springprojects.bankapp.model.TransactionException;
import ft.springprojects.bankapp.model.User;
import ft.springprojects.bankapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransactionValidatorImpl implements TransactionValidator {

    private final UserRepository userRepository;

    @Override
    public void validateTransfer(BigDecimal amount, String principal, Long receiverId) {
        Optional<User> senderOptional = userRepository.findByEmail(principal);
        if(senderOptional.isEmpty()) throw new TransactionException(TransactionExceptions.INVALID_SENDER, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        User sender = senderOptional.get();
        validateReceiver(receiverId);
        validateTransferAmount(sender.getBalance(), amount);
    }

    private void validateTransferAmount(BigDecimal senderBalance, BigDecimal amount){
        if(senderBalance.compareTo(amount) < 0) throw new TransactionException(TransactionExceptions.NOT_ENOUGH_BALANCE, HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }

    private void validateReceiver(Long receiverId){
        Optional<User> receiverOptional = userRepository.findById(receiverId);
        if(receiverOptional.isEmpty()) throw new TransactionException(TransactionExceptions.INVALID_RECEIVER, HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }
}
