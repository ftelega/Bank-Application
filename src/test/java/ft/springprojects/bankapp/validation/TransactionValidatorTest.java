package ft.springprojects.bankapp.validation;

import ft.springprojects.bankapp.model.TransactionException;
import ft.springprojects.bankapp.model.User;
import ft.springprojects.bankapp.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class TransactionValidatorTest {

    private final TransactionValidator transactionValidator;
    private final UserRepository userRepository;

    public TransactionValidatorTest(){
        this.userRepository = mock(UserRepository.class);
        this.transactionValidator = new TransactionValidatorImpl(userRepository);
    }

    @Test
    public void givenRepositoryFindsSenderAndReceiver_whenValidatingTransfer_thenNotThrowException(){
        given(userRepository.findByEmail(any())).willReturn(Optional.of(User.builder().balance(BigDecimal.TEN).build()));
        given(userRepository.findById(any())).willReturn(Optional.of(User.builder().email("test").build()));

        assertDoesNotThrow(() -> {
            transactionValidator.validateTransfer(BigDecimal.TEN, null, null);
        });
    }

    @Test
    public void givenSenderHasNotEnoughBalance_whenValidatingTransfer_thenThrowException(){
        given(userRepository.findByEmail(any())).willReturn(Optional.of(User.builder().balance(BigDecimal.ZERO).build()));
        given(userRepository.findById(any())).willReturn(Optional.of(new User()));

        assertThrows(TransactionException.class, () -> {
            transactionValidator.validateTransfer(BigDecimal.TEN, null, null);
        });
    }

    @Test
    public void givenSenderDoesntExist_whenValidatingTransfer_thenThrowException(){
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());
        given(userRepository.findById(any())).willReturn(Optional.of(new User()));

        assertThrows(TransactionException.class, () -> {
            transactionValidator.validateTransfer(BigDecimal.TEN, null, null);
        });
    }

    @Test
    public void givenInvalidReceiverId_whenValidatingTransfer_thenThrowException(){
        given(userRepository.findByEmail(any())).willReturn(Optional.of(User.builder().balance(BigDecimal.TEN).build()));
        given(userRepository.findById(any())).willReturn(Optional.empty());

        assertThrows(TransactionException.class, () -> {
            transactionValidator.validateTransfer(BigDecimal.TEN, null, null);
        });
    }

    @Test
    public void givenSenderAndReceiverSameId_whenValidatingTransfer_thenThrowException(){
        String principal = "Test";
        given(userRepository.findByEmail(any())).willReturn(Optional.of(User.builder().balance(BigDecimal.TEN).build()));
        given(userRepository.findById(any())).willReturn(Optional.of(User.builder().email(principal).build()));

        assertThrows(TransactionException.class, () -> {
            transactionValidator.validateTransfer(BigDecimal.TEN, principal, null);
        });
    }

    @Test
    public void givenIncorrectAmount_whenValidatingTransfer_thenThrowException(){
        given(userRepository.findByEmail(any())).willReturn(Optional.of(User.builder().balance(BigDecimal.ZERO).build()));
        given(userRepository.findById(any())).willReturn(Optional.of(User.builder().email("test").build()));

        assertThrows(TransactionException.class, () -> {
            transactionValidator.validateTransfer(BigDecimal.ZERO, null, null);
        });
    }

    @Test
    public void givenCorrectSenderAndAmount_whenValidatingDeposit_thenNotThrowException(){
        given(userRepository.findByEmail(any())).willReturn(Optional.of(User.builder().balance(BigDecimal.TEN).build()));

        assertDoesNotThrow(() -> {
            transactionValidator.validateDeposit(BigDecimal.TEN, null);
        });
    }

    @Test
    public void givenIncorrectSender_whenValidatingDeposit_thenNotThrowException(){
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        assertThrows(TransactionException.class, () -> {
            transactionValidator.validateDeposit(BigDecimal.TEN, null);
        });
    }

    @Test
    public void givenIncorrectAmount_whenValidatingDeposit_thenNotThrowException(){
        given(userRepository.findByEmail(any())).willReturn(Optional.of(User.builder().balance(BigDecimal.TEN).build()));

         assertThrows(TransactionException.class, () -> {
            transactionValidator.validateDeposit(BigDecimal.ZERO, null);
        });
    }
}