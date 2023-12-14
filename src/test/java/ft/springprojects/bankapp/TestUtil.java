package ft.springprojects.bankapp;

import ft.springprojects.bankapp.dto.AddressDTO;
import ft.springprojects.bankapp.dto.UserDTO;

import java.math.BigDecimal;

public class TestUtil {

    public static final String CORRECT_USERNAME = "username";
    public static final String CORRECT_EMAIL = "email@email.com";
    public static final String CORRECT_PASSWORD = "Password1@";
    public static final String CORRECT_CITY = "Warsaw";
    public static final String CORRECT_STREET = "Street";
    public static final String CORRECT_STNUMBER = "12/34";
    public static final String TEST_AUTHORITY = "TEST";
    public static final String VALID_JWT = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKV1QiLCJlbWFpbCI6InRlc3QiLCJhdXRob3JpdGllcyI6IlVTRVIiLCJpYXQiOjE3MDI1NTQ4NjcsImV4cCI6MTcwMjU4MzY2N30.1Mvxh8D-lhO7CnjnH8KMFJ9DHJlUa10_MbZqeRyIOBw3xi9kEy9esxqXA6yTIyP1CWORyss4mnksWUEcfSbg_Q";
    public static final String JWT_HEADER = "jwt";
    public static final String TEST_SECURITY_PRINCIPAL = "test";
    public static final String TEST_SECURITY_CREDENTIALS = "test";
    public static final AddressDTO CORRECT_ADDRESSDTO = new AddressDTO(
            CORRECT_CITY,
            CORRECT_STREET,
            CORRECT_STNUMBER
    );
    public static final UserDTO CORRECT_USERDTO = new UserDTO(
            CORRECT_USERNAME,
            CORRECT_EMAIL,
            CORRECT_PASSWORD,
            BigDecimal.ZERO,
            CORRECT_ADDRESSDTO
    );
    public static final UserDTO EMPTY_USERNAME_USERDTO = new UserDTO(
            "",
            CORRECT_EMAIL,
            CORRECT_PASSWORD,
            BigDecimal.ZERO,
            new AddressDTO(CORRECT_CITY, CORRECT_STREET, CORRECT_STNUMBER)
    );
    public static final UserDTO EMPTY_EMAIL_USERDTO = new UserDTO(
            CORRECT_USERNAME,
            "",
            CORRECT_PASSWORD,
            BigDecimal.ZERO,
            new AddressDTO(CORRECT_CITY, CORRECT_STREET, CORRECT_STNUMBER)
    );
    public static final UserDTO EMPTY_PASSWORD_USERDTO = new UserDTO(
            CORRECT_USERNAME,
            CORRECT_EMAIL,
            "",
            BigDecimal.ZERO,
            new AddressDTO(CORRECT_CITY, CORRECT_STREET, CORRECT_STNUMBER)
    );
    public static final UserDTO NULL_USERNAME_USERDTO = new UserDTO(
            null,
            CORRECT_EMAIL,
            CORRECT_PASSWORD,
            BigDecimal.ZERO,
            new AddressDTO(CORRECT_CITY, CORRECT_STREET, CORRECT_STNUMBER)
    );
    public static final UserDTO NULL_EMAIL_USERDTO = new UserDTO(
            CORRECT_USERNAME,
            null,
            CORRECT_PASSWORD,
            BigDecimal.ZERO,
            new AddressDTO(CORRECT_CITY, CORRECT_STREET, CORRECT_STNUMBER)
    );
    public static final UserDTO NULL_PASSWORD_USERDTO = new UserDTO(
            CORRECT_USERNAME,
            CORRECT_EMAIL,
            null,
            BigDecimal.ZERO,
            new AddressDTO(CORRECT_CITY, CORRECT_STREET, CORRECT_STNUMBER)
    );
    public static final AddressDTO EMPTY_CITY_ADDRESSDTO = new AddressDTO(
            "",
            CORRECT_STREET,
            CORRECT_STNUMBER
    );
    public static final AddressDTO NULL_CITY_ADDRESSDTO = new AddressDTO(
            null,
            CORRECT_STREET,
            CORRECT_STNUMBER
    );
    public static final AddressDTO EMPTY_STREET_ADDRESSDTO = new AddressDTO(
            CORRECT_CITY,
            "",
            CORRECT_STNUMBER
    );
    public static final AddressDTO NULL_STREET_ADDRESSDTO = new AddressDTO(
            CORRECT_CITY,
            null,
            CORRECT_STNUMBER
    );
    public static final AddressDTO EMPTY_STNUMBER_ADDRESSDTO = new AddressDTO(
            CORRECT_CITY,
            CORRECT_STREET,
            ""
    );
    public static final AddressDTO NULL_STNUMBER_ADDRESSDTO = new AddressDTO(
            CORRECT_CITY,
            CORRECT_STREET,
            null
    );
}
