package ft.springprojects.bankapp.dto;

import java.math.BigDecimal;

public record UserDTO(
        String username,
        String email,
        String password,
        BigDecimal balance,
        AddressDTO address
) {
}
