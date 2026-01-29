package br.com.terracota.infra.api;

import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.infra.api.dto.request.CreateCustomerRequest;
import br.com.terracota.infra.api.dto.request.UpdateCustomerRequest;
import br.com.terracota.infra.api.dto.response.CreateCustomerResponse;
import br.com.terracota.infra.api.dto.response.CustomerResponse;
import br.com.terracota.infra.api.dto.response.ExceptionResponse;
import br.com.terracota.infra.api.dto.response.UserResponseWithoutAddress;
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
                    @ApiResponse(responseCode = "204", description = "User deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Customer not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @PreAuthorize("@securityService.isCustomerAuthenticated(#id, authentication)")
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable String id);

    @Operation(summary = "Search customers", description = "Search customers with pagination.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List customers successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseWithoutAddress.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
            }
    )
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Pagination<CustomerResponse>> search(
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
