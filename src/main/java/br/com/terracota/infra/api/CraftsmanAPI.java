package br.com.terracota.infra.api;

import br.com.terracota.infra.dto.request.CreateCraftsmanRequest;
import br.com.terracota.infra.dto.response.CreateCraftsmanResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/craftsmans")
public interface CraftsmanAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CreateCraftsmanResponse> create(@Valid @RequestBody CreateCraftsmanRequest request);
}
