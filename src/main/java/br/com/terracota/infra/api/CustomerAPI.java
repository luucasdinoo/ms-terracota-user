package br.com.terracota.infra.api;

import br.com.terracota.infra.api.dto.request.CreateCustomerRequest;
import br.com.terracota.infra.api.dto.response.CreateCustomerResponse;
import br.com.terracota.infra.api.dto.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/customers")
public interface CustomerAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreateCustomerResponse> create(@Valid @RequestBody CreateCustomerRequest request);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerResponse> getById(@PathVariable String id);
}
