package br.com.terracota.infra.api;

import br.com.terracota.infra.api.dto.request.CreateCraftsmanRequest;
import br.com.terracota.infra.api.dto.response.CraftsmanResponse;
import br.com.terracota.infra.api.dto.response.CreateCraftsmanResponse;
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
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreateCraftsmanResponse> create(@Valid @RequestBody CreateCraftsmanRequest request);

    @Operation(summary = "Get craftsman by id", description = "Retrieves a craftsman by their unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get craftsman by id successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CraftsmanResponse.class))),
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CraftsmanResponse> getById(@PathVariable String id);

}
