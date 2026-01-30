package br.com.terracota.infra.security.user;

import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String passwordInput = authentication.getCredentials().toString();

        User user = this.userGateway.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Incorrect username or password"));

        boolean correctPassword = passwordEncoder.matches(passwordInput, user.getPassword());

        if (correctPassword){
            return new CustomAuthentication(user);
        }

        throw new UsernameNotFoundException("Incorrect username or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
