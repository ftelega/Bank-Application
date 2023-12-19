package ft.springprojects.bankapp.controller;

import ft.springprojects.bankapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(path = "/transfer")
    @ResponseStatus(HttpStatus.OK)
    public void transfer(@RequestParam(name = "receiver") Long receiverId, @RequestParam(name = "amount") BigDecimal amount){
        transactionService.transfer(amount, receiverId);
    }
}
