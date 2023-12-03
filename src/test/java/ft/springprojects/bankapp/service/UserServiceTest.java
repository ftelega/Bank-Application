package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.dto.AddressDTO;
import ft.springprojects.bankapp.dto.UserDTO;
import ft.springprojects.bankapp.repository.UserRepository;
import ft.springprojects.bankapp.validation.AddressValidator;
import ft.springprojects.bankapp.validation.UserValidator;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static ft.springprojects.bankapp.TestUtil.*;

class UserServiceTest {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final AddressValidator addressValidator;
    private final PasswordEncoder passwordEncoder;

    public UserServiceTest() {
        this.userRepository = mock(UserRepository.class);
        this.userValidator = mock(UserValidator.class);
        this.addressValidator = mock(AddressValidator.class);
        this.passwordEncoder = mock(PasswordEncoder.class);
        this.userService = new UserServiceImpl(userRepository, passwordEncoder, userValidator, addressValidator, mock(UserAuthorityService.class));
    }

    @Test
    public void givenUser_whenCreateUser_thenPersist(){
        UserDTO userDTO = new UserDTO(
                CORRECT_USERNAME,
                CORRECT_EMAIL,
                CORRECT_PASSWORD,
                BigDecimal.ZERO,
                new AddressDTO(CORRECT_CITY, CORRECT_STREET, CORRECT_STNUMBER)
        );

        userService.createUser(userDTO);

        verify(userValidator, times(1)).validateUser(userDTO);
        verify(addressValidator, times(1)).validateAddress(userDTO.address());
        verify(passwordEncoder, times(1)).encode(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void givenUserValidatorThrowsException_whenCreateUser_thenNotPersist(){

    }

    @Test
    public void givenAddressValidatorThrowsException_whenCreateUser_thenNotPersist(){

    }
}