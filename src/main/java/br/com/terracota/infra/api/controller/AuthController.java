package br.com.terracota.infra.api.controller;

import br.com.terracota.application.dto.input.AuthLoginInput;
import br.com.terracota.application.dto.output.AuthLoginOutput;
import br.com.terracota.application.usecase.AuthLoginUseCase;
import br.com.terracota.infra.api.AuthAPI;
import br.com.terracota.infra.api.dto.request.AuthLoginRequest;
import br.com.terracota.infra.api.dto.response.AuthLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthAPI {

    private final AuthLoginUseCase loginUseCase;

    @Override
    public ResponseEntity<AuthLoginResponse> login(final AuthLoginRequest request) {
        var loginInput = AuthLoginInput.with(request.username(), request.password());
        AuthLoginOutput loginOutput = this.loginUseCase.execute(loginInput);
        return ResponseEntity.ok(new AuthLoginResponse(loginOutput.accessToken()));
    }
}
