package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.model.TransactionException;
import ft.springprojects.bankapp.model.User;
import ft.springprojects.bankapp.repository.TransactionRepository;
import ft.springprojects.bankapp.validation.TransactionValidator;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class TransactionServiceTest {

    private final TransactionService transactionService;
    private final TransactionValidator transactionValidator;
    private final TransactionRepository transactionRepository;
    private final UserTransactionService userTransactionService;

    public TransactionServiceTest() {
        this.transactionValidator = mock(TransactionValidator.class);
        this.transactionRepository = mock(TransactionRepository.class);
        this.userTransactionService = mock(UserTransactionService.class);
        this.transactionService = new TransactionServiceImpl(transactionRepository, transactionValidator, userTransactionService);
    }

    @Test
    public void whenTransferring_thenVerifyCalls(){
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("principal", null));
        User sender = User.builder().balance(BigDecimal.TEN).build();
        given(userTransactionService.getTransferSender(any())).willReturn(sender);

        transactionService.transfer(BigDecimal.TEN, null);

        assertEquals(BigDecimal.ZERO, sender.getBalance());
        verify(transactionValidator, times(1)).validateTransfer(any(), any(), any());
        verify(userTransactionService, times(1)).getTransferSender(any());
        verify(userTransactionService, times(1)).getTransferReceiver(any());
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    public void givenValidatorThrowsException_whenTransferring_thenThrowException(){
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("principal", null));
        doThrow(TransactionException.class).when(transactionValidator).validateTransfer(any(), any(), any());

        assertThrows(TransactionException.class, () -> {
            transactionService.transfer(null, null);
        });

        verify(transactionValidator, times(1)).validateTransfer(any(), any(), any());
        verify(userTransactionService, times(0)).getTransferSender(any());
        verify(userTransactionService, times(0)).getTransferReceiver(any());
        verify(transactionRepository, times(0)).save(any());
    }
}