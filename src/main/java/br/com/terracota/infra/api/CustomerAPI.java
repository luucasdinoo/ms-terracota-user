package br.com.terracota.infra.api;

import br.com.terracota.infra.dto.request.CreateCustomerRequest;
import br.com.terracota.infra.dto.response.CreateCustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/customers")
public interface CustomerAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreateCustomerResponse> create(@Valid @RequestBody CreateCustomerRequest request);
}
