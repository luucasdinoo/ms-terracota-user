package br.com.terracota.application.usecase.auth;

import br.com.terracota.application.dto.output.AuthLoginOutput;
import br.com.terracota.infra.security.jwt.JwtUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static br.com.terracota.mock.UseCaseTestMocks.AUTH_LOGIN_INPUT;
import static br.com.terracota.mock.UseCaseTestMocks.AUTH_LOGIN_OUTPUT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthLoginUseCaseTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthLoginUseCase useCase;

    @Test
    @DisplayName(" ")
    void test1(){
        Authentication authentication = mock(Authentication.class);

        when(this.authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(this.jwtUtils.generateTokenForUser(any(Authentication.class)))
                .thenReturn(AUTH_LOGIN_OUTPUT);

        this.useCase.execute(AUTH_LOGIN_INPUT);

        assertThat(authentication).isEqualTo(SecurityContextHolder.getContext().getAuthentication());
    }
}
