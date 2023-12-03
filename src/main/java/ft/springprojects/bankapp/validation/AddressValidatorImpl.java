package ft.springprojects.bankapp.validation;

import ft.springprojects.bankapp.dto.AddressDTO;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AddressValidatorImpl implements AddressValidator {

    private static final Pattern CITY_PATTERN = Pattern.compile("^[a-zA-Z]{3,20}$");
    private static final Pattern STREET_PATTERN = Pattern.compile("^[a-zA-Z]{3,20}$");
    private static final Pattern STNUMBER_PATTERN = Pattern.compile("^(?=.*[0-9])[0-9/]$");
    @Override
    public void validateAddress(AddressDTO addressDTO) {

    }
}
