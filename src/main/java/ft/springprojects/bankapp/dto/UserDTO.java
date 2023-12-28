package ft.springprojects.bankapp.dto;

public record UserDTO(
        String username,
        String email,
        String password,
        AddressDTO address
) {
}
