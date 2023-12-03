package ft.springprojects.bankapp.validation;

import ft.springprojects.bankapp.dto.AddressDTO;

public interface AddressValidator {

    void validateAddress(AddressDTO addressDTO);
}
