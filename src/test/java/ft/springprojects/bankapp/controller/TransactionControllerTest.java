package ft.springprojects.bankapp.controller;

import ft.springprojects.bankapp.enums.TransactionExceptions;
import ft.springprojects.bankapp.model.TransactionException;
import ft.springprojects.bankapp.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.BDDMockito.*;

@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureJsonTesters
class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService;
    @MockBean
    private DateTimeFormatter dateTimeFormatter;
    private final MockMvc mockMvc;

    @Autowired
    public TransactionControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void whenTransferring_thenStatusOk() throws Exception{

        ResultActions res = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/v1/transaction/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .param("receiver", "1")
                .param("amount", "123")
        );

        res.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenServiceThrowsException_whenTransferring_thenStatusBadRequest() throws Exception{
        doThrow(new TransactionException(TransactionExceptions.INVALID_RECEIVER, HttpStatus.BAD_REQUEST, LocalDateTime.now())).when(transactionService).transfer(any(), any());

        ResultActions res = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/v1/transaction/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .param("receiver", "1")
                .param("amount", "123")
        );

        res.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}