package br.com.terracota.infra.api;

import br.com.terracota.infra.api.dto.request.AuthLoginRequest;
import br.com.terracota.infra.api.dto.response.AuthLoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Authentication", description = "Authentication management API")
@RequestMapping("/api/v1/auth")
public interface AuthAPI {

    @Operation(summary = "Authenticate user", description = "Authenticates a user and returns a JWT token.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User authenticated successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthLoginResponse.class))),
            }
    )
    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<AuthLoginResponse> login(@Valid @RequestBody AuthLoginRequest request);
}
