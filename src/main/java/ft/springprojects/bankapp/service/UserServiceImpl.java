package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.dto.UserDTO;
import ft.springprojects.bankapp.model.User;
import ft.springprojects.bankapp.repository.UserRepository;
import ft.springprojects.bankapp.util.Mapper;
import ft.springprojects.bankapp.validation.AddressValidator;
import ft.springprojects.bankapp.validation.TransactionValidator;
import ft.springprojects.bankapp.validation.UserValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;
    private final AddressValidator addressValidator;
    private final UserAuthorityService userAuthorityService;
    private final TransactionValidator transactionValidator;

    @Transactional
    @Override
    public void createUser(UserDTO userDTO) {
        userValidator.validateUser(userDTO);
        addressValidator.validateAddress(userDTO.address());
        User user = Mapper.mapToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getAuthorities().add(userAuthorityService.getAuthority("USER"));
        userRepository.save(user);
        log.info("user {} has been persisted", userDTO.username());
    }

    @Override
    public void login() {
        log.info("user {} has logged in", SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    @Transactional
    public void deposit(BigDecimal amount) {
        String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        transactionValidator.validateDeposit(amount, principal);
        User user = userRepository.findByEmail(principal).get();
        user.setBalance(user.getBalance().add(amount));
        userRepository.save(user);
        log.info("user {} has deposited {} amount", user.getEmail(), amount);
    }
}
