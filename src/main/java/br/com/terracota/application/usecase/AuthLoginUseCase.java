package br.com.terracota.application.usecase;

import br.com.terracota.application.UseCase;
import br.com.terracota.application.dto.input.AuthLoginInput;
import br.com.terracota.infra.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthLoginUseCase extends UseCase<AuthLoginInput, AuthLoginOutput> {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public AuthLoginOutput execute(final AuthLoginInput input) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.username(), input.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtils.generateTokenForUser(authentication);
        return new AuthLoginOutput(jwt);
    }
}
