package br.com.terracota.infra.api;

import br.com.terracota.infra.api.dto.request.AuthLoginRequest;
import br.com.terracota.infra.api.dto.response.AuthLoginResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
public interface AuthAPI {

    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<AuthLoginResponse> login(@Valid @RequestBody AuthLoginRequest request);
}
