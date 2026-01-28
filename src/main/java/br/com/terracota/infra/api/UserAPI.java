package br.com.terracota.infra.api;

import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.infra.api.dto.request.CreateUserRequest;
import br.com.terracota.infra.api.dto.response.CreateUserResponse;
import br.com.terracota.infra.api.dto.response.ExceptionResponse;
import br.com.terracota.infra.api.dto.response.UserResponse;
import br.com.terracota.infra.api.dto.response.UserResponseWithoutAddress;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@Tag(name = "Users", description = "User management API")
@RequestMapping("/api/v1/users")
public interface UserAPI {

    @Operation(summary = "Create a new User", description = "Creates a new user in the system.",
            responses = {
                @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateUserResponse.class))),
                @ApiResponse(responseCode = "400", description = "Invalid parameters",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                @ApiResponse(responseCode = "409", description = "User already exists",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                @ApiResponse(responseCode = "500", description = "Internal server error",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreateUserResponse> create(@Valid @RequestBody CreateUserRequest request);

    @Operation(summary = "Get user by id", description = "Retrieves a user by their unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get user by id successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseWithoutAddress.class))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponse> getById(@PathVariable String id);

    @Operation(summary = "Search users", description = "Search users with pagination.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List users successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseWithoutAddress.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Pagination<UserResponseWithoutAddress>> search(
            @RequestParam(required = false) final String username,
            @RequestParam(required = false) final String email,
            @RequestParam(required = false) final String name,
            @RequestParam(required = false) final String document,
            @RequestParam(required = false, defaultValue = "0") final int page,
            @RequestParam(required = false, defaultValue = "10") final int perPage,
            @RequestParam(required = false, defaultValue = "name") final String sort,
            @RequestParam(required = false, defaultValue = "asc") final String dir
    );
}
