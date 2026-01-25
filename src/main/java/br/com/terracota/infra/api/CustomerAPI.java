package br.com.terracota.infra.api;

import br.com.terracota.infra.api.dto.request.CreateCustomerRequest;
import br.com.terracota.infra.api.dto.response.CreateCustomerResponse;
import br.com.terracota.infra.api.dto.response.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customers", description = "Customer management API")
@RequestMapping("/api/v1/customers")
public interface CustomerAPI {

    @Operation(summary = "Create a new customer", description = "Creates a new customer in the system.",
            responses = {
                @ApiResponse(responseCode = "201", description = "Customer created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateCustomerResponse.class))),
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreateCustomerResponse> create(@Valid @RequestBody CreateCustomerRequest request);

    @Operation(summary = "Get customer by id", description = "Retrieves a customer by their unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get customer by id successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))),
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerResponse> getById(@PathVariable String id);
}
