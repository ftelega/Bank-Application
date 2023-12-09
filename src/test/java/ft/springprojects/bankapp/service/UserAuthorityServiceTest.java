package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.model.Authority;
import ft.springprojects.bankapp.repository.AuthorityRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class UserAuthorityServiceTest {

    private final AuthorityRepository authorityRepository;
    private final UserAuthorityService userAuthorityService;

    public UserAuthorityServiceTest() {
        this.authorityRepository = mock(AuthorityRepository.class);
        this.userAuthorityService = new UserAuthorityServiceImpl(authorityRepository);
    }

    @Test
    public void givenAuthorityExists_whenGetAuthority_thenAuthorityNotNull(){
        given(authorityRepository.findByName(any())).willReturn(Optional.of(new Authority()));

        Authority authority = userAuthorityService.getAuthority("");

        assertNotNull(authority);
    }

    @Test
    public void givenAuthorityNotExists_whenGetAuthority_thenAuthorityNotNull(){
        given(authorityRepository.findByName(any())).willReturn(Optional.empty());

        Authority authority = userAuthorityService.getAuthority("");

        assertNotNull(authority);
    }


}