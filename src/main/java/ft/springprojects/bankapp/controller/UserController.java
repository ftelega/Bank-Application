package ft.springprojects.bankapp.controller;

import ft.springprojects.bankapp.dto.UserDTO;
import ft.springprojects.bankapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/create")
    @ResponseStatus(value = HttpStatus.OK)
    public void createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
    }

    @GetMapping(path = "/login")
    @ResponseStatus(value = HttpStatus.OK)
    public void login(){
        userService.login();
    }

    @PostMapping(path = "/deposit")
    @ResponseStatus(value = HttpStatus.OK)
    public void deposit(@RequestParam(name = "amount") BigDecimal amount) {
        userService.deposit(amount);
    }
}
