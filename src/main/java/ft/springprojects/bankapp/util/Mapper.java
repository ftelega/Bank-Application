package ft.springprojects.bankapp.util;

import ft.springprojects.bankapp.dto.AddressDTO;
import ft.springprojects.bankapp.dto.UserDTO;
import ft.springprojects.bankapp.model.Address;
import ft.springprojects.bankapp.model.User;

import java.math.BigDecimal;
import java.util.HashSet;

public class Mapper {

    public static User mapToUser(UserDTO userDTO){
        return User.builder()
                .username(userDTO.username())
                .email(userDTO.email())
                .password(userDTO.password())
                .balance(BigDecimal.ZERO)
                .address(mapToAddress(userDTO.address()))
                .authorities(new HashSet<>())
                .transactionsSent(new HashSet<>())
                .transactionsReceived(new HashSet<>())
                .build();
    }

    public static Address mapToAddress(AddressDTO addressDTO){
        return Address.builder()
                .city(addressDTO.city())
                .street(addressDTO.street())
                .streetNumber(addressDTO.streetNumber())
                .build();
    }
}
