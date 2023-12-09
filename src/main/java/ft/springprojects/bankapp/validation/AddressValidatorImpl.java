package ft.springprojects.bankapp.validation;

import ft.springprojects.bankapp.dto.AddressDTO;
import ft.springprojects.bankapp.enums.AddressExceptions;
import ft.springprojects.bankapp.model.AddressException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Component
public class AddressValidatorImpl implements AddressValidator {

    private static final Pattern CITY_PATTERN = Pattern.compile("^[a-zA-Z]{3,20}$");
    private static final Pattern STREET_PATTERN = Pattern.compile("^[a-zA-Z]{3,20}$");
    private static final Pattern STNUMBER_PATTERN = Pattern.compile("^\\d+(?:/\\d+)?$");
    @Override
    public void validateAddress(AddressDTO addressDTO) {
        validateCity(addressDTO.city());
        validateStreet(addressDTO.street());
        validateStreetNumber(addressDTO.streetNumber());
    }

    private void validateCity(String city){
        if(city == null || !CITY_PATTERN.matcher(city).matches()) throw new AddressException(AddressExceptions.INVALID_CITY, HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }

    private void validateStreet(String street){
        if(street == null || !STREET_PATTERN.matcher(street).matches()) throw new AddressException(AddressExceptions.INVALID_STREET, HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }

    private void validateStreetNumber(String streetNumber){
       if(streetNumber == null || !STNUMBER_PATTERN.matcher(streetNumber).matches()) throw new AddressException(AddressExceptions.INVALID_STNUMBER, HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }
}
