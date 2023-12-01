package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.dto.UserDTO;

public interface UserService {

    void createUser(UserDTO userDTO);
    void login();
}
