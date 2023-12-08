package ft.springprojects.bankapp.provider;

import ft.springprojects.bankapp.model.Authority;
import ft.springprojects.bankapp.model.User;
import ft.springprojects.bankapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()) throw new BadCredentialsException("User does not exist");
        User user = userOptional.get();
        if(!passwordEncoder.matches(password, user.getPassword())) throw new BadCredentialsException("Bad Credentials");
        return new UsernamePasswordAuthenticationToken(
                email,
                null,
                extractAuthorities(user.getAuthorities())
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

    private Collection<? extends GrantedAuthority> extractAuthorities(Collection<Authority> authorities){
        Set<GrantedAuthority> res = new HashSet<>();
        for(Authority auth : authorities){
            res.add(new SimpleGrantedAuthority(auth.getName()));
        }
        return res;
    }
}
