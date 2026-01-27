package br.com.terracota.infra.api;

import br.com.terracota.infra.api.dto.request.CreateCraftsmanRequest;
import br.com.terracota.infra.api.dto.request.UpdateCraftsmanRequest;
import br.com.terracota.infra.api.dto.request.UpdateCustomerRequest;
import br.com.terracota.infra.api.dto.response.CraftsmanResponse;
import br.com.terracota.infra.api.dto.response.CreateCraftsmanResponse;
import br.com.terracota.infra.api.dto.response.CreateCustomerResponse;
import br.com.terracota.infra.api.dto.response.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> update(@PathVariable String id, @RequestBody UpdateCraftsmanRequest request);
}
