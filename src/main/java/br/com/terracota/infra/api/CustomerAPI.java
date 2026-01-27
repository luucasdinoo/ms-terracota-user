package br.com.terracota.infra.api;

import br.com.terracota.infra.api.dto.request.CreateCustomerRequest;
import br.com.terracota.infra.api.dto.request.UpdateCustomerRequest;
import br.com.terracota.infra.api.dto.response.CreateCustomerResponse;
import br.com.terracota.infra.api.dto.response.CustomerResponse;
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

//TODO: Search customers with filters and pagination
@Tag(name = "Customers", description = "Customer management API")
@RequestMapping("/api/v1/customers")
public interface CustomerAPI {

    @Operation(summary = "Create a new customer", description = "Creates a new customer in the system.",
            responses = {
                @ApiResponse(responseCode = "201", description = "Customer created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateCustomerResponse.class))),
                @ApiResponse(responseCode = "400", description = "Invalid parameters",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                @ApiResponse(responseCode = "409", description = "Customer already exists",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreateCustomerResponse> create(@Valid @RequestBody CreateCustomerRequest request);

    @Operation(summary = "Get customer by id", description = "Retrieves a customer by their unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get customer by id successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Customer not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerResponse> getById(@PathVariable String id);

    @Operation(summary = "Get customer by document", description = "Retrieves a customer by document identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get customer by id successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Customer not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @GetMapping(value = "/document/{document}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerResponse> getByDocument(@PathVariable String document);

    @Operation(summary = "Update customer", description = "update customer in the system.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Customer updated successfully"),
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
    @PreAuthorize("@securityService.isCustomerAuthenticated(#id, authentication)")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> update(@PathVariable String id, @RequestBody UpdateCustomerRequest request);

    @Operation(summary = "Delete customer", description = "delete customer in the system.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Customer updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Customer not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @PreAuthorize("@securityService.isCustomerAuthenticated(#id, authentication)")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable String id);
}
