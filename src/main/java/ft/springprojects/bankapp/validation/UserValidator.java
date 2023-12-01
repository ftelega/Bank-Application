package ft.springprojects.bankapp.validation;

import ft.springprojects.bankapp.dto.UserDTO;

public interface UserValidator {

    void validateUser(UserDTO userDTO);
}
