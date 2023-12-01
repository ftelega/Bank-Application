package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.dto.UserDTO;
import ft.springprojects.bankapp.model.User;
import ft.springprojects.bankapp.repository.UserRepository;
import ft.springprojects.bankapp.util.Mapper;
import ft.springprojects.bankapp.validation.UserValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    @Transactional
    @Override
    public void createUser(UserDTO userDTO) {
        userValidator.validateUser(userDTO);
        User user = Mapper.mapToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("user {} has been persisted", userDTO.username());
    }

    @Override
    public void login() {
        log.info("user {} has logged in", SecurityContextHolder.getContext().getAuthentication().getName());
    }
}