package br.com.terracota.infra.api;

import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.infra.api.dto.request.CreateCraftsmanRequest;
import br.com.terracota.infra.api.dto.request.UpdateCraftsmanRequest;
import br.com.terracota.infra.api.dto.response.CraftsmanResponse;
import br.com.terracota.infra.api.dto.response.CreateCraftsmanResponse;
import br.com.terracota.infra.api.dto.response.ExceptionResponse;
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

@Tag(name = "Craftsmen", description = "Craftsman management API")
@RequestMapping("/api/v1/craftsmen")
public interface CraftsmanAPI {

    @Operation(summary = "Create a new craftsman", description = "Creates a new craftsman in the system.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Craftsman created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateCraftsmanResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "409", description = "Craftsman already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreateCraftsmanResponse> create(@Valid @RequestBody CreateCraftsmanRequest request);

    @Operation(summary = "Get craftsman by id", description = "Retrieves a craftsman by their unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get craftsman by id successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CraftsmanResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Craftsman not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CraftsmanResponse> getById(@PathVariable String id);

    @Operation(summary = "Get craftsman by document", description = "Retrieves a craftsman by document identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get craftsman by id successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CraftsmanResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Craftsman not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @GetMapping(value = "/document/{document}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CraftsmanResponse> getByDocument(@PathVariable String document);

    @Operation(summary = "Update craftsman", description = "update craftsman in the system.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Craftsman updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "409", description = "Customer already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Customer not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @PreAuthorize("@securityService.isCraftsmanAuthenticated(#id, authentication)")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> update(@PathVariable String id, @RequestBody UpdateCraftsmanRequest request);

    @Operation(summary = "Delete craftsman", description = "Delete craftsman in the system.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Craftsman updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Craftsman not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @PreAuthorize("@securityService.isCraftsmanAuthenticated(#id, authentication)")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable String id);

    @Operation(summary = "Search craftsmen", description = "Search craftsmen with pagination.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List craftsmen successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagination.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Pagination<CraftsmanResponse>> search(
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
