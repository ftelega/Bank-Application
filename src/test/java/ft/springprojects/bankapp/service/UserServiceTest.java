package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.model.AddressException;
import ft.springprojects.bankapp.model.UserException;
import ft.springprojects.bankapp.repository.UserRepository;
import ft.springprojects.bankapp.validation.AddressValidator;
import ft.springprojects.bankapp.validation.UserValidator;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static ft.springprojects.bankapp.TestUtil.*;

class UserServiceTest {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final AddressValidator addressValidator;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityService userAuthorityService;

    public UserServiceTest() {
        this.userRepository = mock(UserRepository.class);
        this.userValidator = mock(UserValidator.class);
        this.addressValidator = mock(AddressValidator.class);
        this.passwordEncoder = mock(PasswordEncoder.class);
        this.userAuthorityService = mock(UserAuthorityService.class);
        this.userService = new UserServiceImpl(userRepository, passwordEncoder, userValidator, addressValidator, userAuthorityService);
    }

    @Test
    public void givenUser_whenCreateUser_thenPersist(){
        userService.createUser(CORRECT_USERDTO);

        verify(userValidator, times(1)).validateUser(CORRECT_USERDTO);
        verify(addressValidator, times(1)).validateAddress(CORRECT_USERDTO.address());
        verify(passwordEncoder, times(1)).encode(any());
        verify(userRepository, times(1)).save(any());
        verify(userAuthorityService, times(1)).getAuthority("USER");
    }

    @Test
    public void givenUserValidatorThrowsException_whenCreateUser_thenNotPersist(){
        doThrow(UserException.class).when(userValidator).validateUser(any());

        assertThrows(UserException.class, () -> userService.createUser(CORRECT_USERDTO));

        verify(userValidator, times(1)).validateUser(CORRECT_USERDTO);
        verify(userRepository, times(0)).save(any());
    }

    @Test
    public void givenAddressValidatorThrowsException_whenCreateUser_thenNotPersist(){
        doThrow(AddressException.class).when(addressValidator).validateAddress(any());

        assertThrows(AddressException.class, () -> userService.createUser(CORRECT_USERDTO));

        verify(addressValidator, times(1)).validateAddress(CORRECT_USERDTO.address());
        verify(userRepository, times(0)).save(any());
    }
}