package ft.springprojects.bankapp.validation;

import ft.springprojects.bankapp.model.AddressException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ft.springprojects.bankapp.TestUtil.*;

class AddressValidatorTest {

    private final AddressValidator addressValidator;

    public AddressValidatorTest() {
        this.addressValidator = new AddressValidatorImpl();
    }

    @Test
    public void givenCorrectAddress_whenValidate_thenNoException(){
        assertDoesNotThrow(() -> {
            addressValidator.validateAddress(CORRECT_ADDRESSDTO);
        });
    }

    @Test
    public void givenIncorrectCity1_whenValidate_thenException(){
        assertThrows(AddressException.class, () -> {
            addressValidator.validateAddress(EMPTY_CITY_ADDRESSDTO);
        });
    }

    @Test
    public void givenIncorrectCity2_whenValidate_thenException(){
        assertThrows(AddressException.class, () -> {
            addressValidator.validateAddress(NULL_CITY_ADDRESSDTO);
        });
    }

    @Test
    public void givenIncorrectStreet1_whenValidate_thenException(){
        assertThrows(AddressException.class, () -> {
            addressValidator.validateAddress(EMPTY_STREET_ADDRESSDTO);
        });
    }

    @Test
    public void givenIncorrectStreet2_whenValidate_thenException(){
        assertThrows(AddressException.class, () -> {
            addressValidator.validateAddress(NULL_STREET_ADDRESSDTO);
        });
    }

    @Test
    public void givenIncorrectStreetNumber1_whenValidate_thenException(){
        assertThrows(AddressException.class, () -> {
            addressValidator.validateAddress(EMPTY_STNUMBER_ADDRESSDTO);
        });
    }

    @Test
    public void givenIncorrectStreetNumber2_whenValidate_thenException(){
        assertThrows(AddressException.class, () -> {
            addressValidator.validateAddress(NULL_STNUMBER_ADDRESSDTO);
        });
    }

    @Test
    public void givenNoAddress_whenValidate_thenException(){
        assertThrows(AddressException.class, () -> {
            addressValidator.validateAddress(null);
        });
    }
}