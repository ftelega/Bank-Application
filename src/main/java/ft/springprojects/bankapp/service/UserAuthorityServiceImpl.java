package ft.springprojects.bankapp.service;

import ft.springprojects.bankapp.model.Authority;
import ft.springprojects.bankapp.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthorityServiceImpl implements UserAuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public Authority getAuthority(String name) {
        return authorityRepository
                .findByName(name)
                .orElse(Authority.builder().name(name).build());
    }
}
