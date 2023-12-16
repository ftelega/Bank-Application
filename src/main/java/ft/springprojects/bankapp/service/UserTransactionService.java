package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.model.User;

public interface UserTransactionService {

    User getTransferSender(String principal);
    User getTransferReceiver(Long receiverId);
}
