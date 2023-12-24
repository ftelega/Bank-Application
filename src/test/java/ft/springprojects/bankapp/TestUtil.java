package ft.springprojects.bankapp;

import ft.springprojects.bankapp.config.SecurityConstants;
import ft.springprojects.bankapp.dto.AddressDTO;
import ft.springprojects.bankapp.dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static ft.springprojects.bankapp.config.SecurityConstants.*;

public class TestUtil {

    public static final String CORRECT_USERNAME = "username";
    public static final String CORRECT_EMAIL = "email@email.com";
    public static final String CORRECT_PASSWORD = "Password1@";
    public static final String CORRECT_CITY = "Warsaw";
    public static final String CORRECT_STREET = "Street";
    public static final String CORRECT_STNUMBER = "12/34";
    public static final String TEST_AUTHORITY = "TEST";
    public static final String JWT_HEADER = SecurityConstants.JWT_HEADER;
    public static String generateJwt(String principal, String authorities){
        SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder()
                .setSubject("JWT")
                .claim("email", principal)
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
                .signWith(key).compact();
        return jwt;
    }
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
            CORRECT_ADDRESSDTO
    );
    public static final UserDTO EMPTY_USERNAME_USERDTO = new UserDTO(
            "",
            CORRECT_EMAIL,
            CORRECT_PASSWORD,
            new AddressDTO(CORRECT_CITY, CORRECT_STREET, CORRECT_STNUMBER)
    );
    public static final UserDTO EMPTY_EMAIL_USERDTO = new UserDTO(
            CORRECT_USERNAME,
            "",
            CORRECT_PASSWORD,
            new AddressDTO(CORRECT_CITY, CORRECT_STREET, CORRECT_STNUMBER)
    );
    public static final UserDTO EMPTY_PASSWORD_USERDTO = new UserDTO(
            CORRECT_USERNAME,
            CORRECT_EMAIL,
            "",
            new AddressDTO(CORRECT_CITY, CORRECT_STREET, CORRECT_STNUMBER)
    );
    public static final UserDTO NULL_USERNAME_USERDTO = new UserDTO(
            null,
            CORRECT_EMAIL,
            CORRECT_PASSWORD,
            new AddressDTO(CORRECT_CITY, CORRECT_STREET, CORRECT_STNUMBER)
    );
    public static final UserDTO NULL_EMAIL_USERDTO = new UserDTO(
            CORRECT_USERNAME,
            null,
            CORRECT_PASSWORD,
            new AddressDTO(CORRECT_CITY, CORRECT_STREET, CORRECT_STNUMBER)
    );
    public static final UserDTO NULL_PASSWORD_USERDTO = new UserDTO(
            CORRECT_USERNAME,
            CORRECT_EMAIL,
            null,
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
