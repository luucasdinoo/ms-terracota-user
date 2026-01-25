package br.com.terracota.infra.api;

import br.com.terracota.infra.api.dto.request.CreateUserRequest;
import br.com.terracota.infra.api.dto.response.CreateUserResponse;
import br.com.terracota.infra.api.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "User management API")
@RequestMapping("/api/v1/users")
public interface UserAPI {

    @Operation(summary = "Create a new User", description = "Creates a new user in the system.",
            responses = {
                @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateUserResponse.class))),
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreateUserResponse> create(@Valid @RequestBody CreateUserRequest request);

    @Operation(summary = "Get user by id", description = "Retrieves a user by their unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get user by id successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponse> getById(@PathVariable String id);
}
