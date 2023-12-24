package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.dto.UserDTO;

import java.math.BigDecimal;

public interface UserService {

    void createUser(UserDTO userDTO);
    void login();
    void deposit(BigDecimal amount);
}
