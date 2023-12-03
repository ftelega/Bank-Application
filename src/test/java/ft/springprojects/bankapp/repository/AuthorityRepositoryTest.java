package ft.springprojects.bankapp.repository;

import ft.springprojects.bankapp.BaseTest;
import ft.springprojects.bankapp.model.Authority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ft.springprojects.bankapp.TestUtil.*;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorityRepositoryTest extends BaseTest {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityRepositoryTest(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @BeforeEach
    public void setup(){
        authorityRepository.deleteAll();
    }

    @Test
    public void givenAuthorityExists_whenFindByName_thenOptionalPresent(){
        authorityRepository.save(Authority.builder().name(TEST_AUTHORITY).build());

        Optional<Authority> authority = authorityRepository.findByName(TEST_AUTHORITY);

        assertTrue(authority.isPresent());
    }

    @Test
    public void givenAuthorityNotExists_whenFindByName_thenOptionalEmpty(){
        Optional<Authority> authority = authorityRepository.findByName(TEST_AUTHORITY);

        assertTrue(authority.isEmpty());
    }
}