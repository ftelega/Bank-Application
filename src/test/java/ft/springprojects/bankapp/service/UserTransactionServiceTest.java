package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.model.User;
import ft.springprojects.bankapp.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class UserTransactionServiceTest {

    private final UserTransactionService userTransactionService;
    private final UserRepository userRepository;

    public UserTransactionServiceTest() {
        this.userRepository = mock(UserRepository.class);
        this.userTransactionService = new UserTransactionServiceImpl(userRepository);
    }

    @Test
    public void givenSenderExists_whenGettingSender_thenReturnSender(){
        given(userRepository.findByEmail(any())).willReturn(Optional.of(new User()));

        User res = userTransactionService.getTransferSender(null);

        assertNotNull(res);
    }

    @Test
    public void givenReceiverExists_whenGettingReceiver_thenReturnReceiver(){
        given(userRepository.findById(any())).willReturn(Optional.of(new User()));

        User res = userTransactionService.getTransferReceiver(null);

        assertNotNull(res);
    }

    @Test
    public void givenSenderNotExists_whenGettingSender_thenReturnNull(){
        given(userRepository.findByEmail(any())).willReturn(Optional.empty());

        User res = userTransactionService.getTransferSender(null);

        assertNull(res);
    }

    @Test
    public void givenReceiverNotExists_whenGettingReceiver_thenReturnNull(){
        given(userRepository.findById(any())).willReturn(Optional.empty());

        User res = userTransactionService.getTransferReceiver(null);

        assertNull(res);
    }
}