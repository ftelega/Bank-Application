package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.model.User;
import ft.springprojects.bankapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTransactionServiceImpl implements UserTransactionService{

    private final UserRepository userRepository;

    @Override
    public User getTransferSender(String principal) {
        return userRepository.findByEmail(principal).orElse(null);
    }

    @Override
    public User getTransferReceiver(Long receiverId) {
        return userRepository.findById(receiverId).orElse(null);
    }
}
