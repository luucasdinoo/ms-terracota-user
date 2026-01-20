package br.com.terracota.infra.api.controller;

import br.com.terracota.application.dto.input.CreateCustomerInput;
import br.com.terracota.application.dto.input.DocumentInput;
import br.com.terracota.application.dto.input.UserInput;
import br.com.terracota.application.dto.output.CreateCustomerOutput;
import br.com.terracota.application.usecase.CreateCustomerUseCase;
import br.com.terracota.infra.api.CustomerAPI;
import br.com.terracota.infra.dto.request.CreateCustomerRequest;
import br.com.terracota.infra.dto.request.DocumentRequest;
import br.com.terracota.infra.dto.request.UserRequest;
import br.com.terracota.infra.dto.response.CreateCustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerAPI {

    private final CreateCustomerUseCase createCustomerUseCase;

    @Override
    public ResponseEntity<CreateCustomerResponse> create(final CreateCustomerRequest request) {
        UserRequest userRequest = request.user();
        DocumentRequest documentRequest = request.document();

        var userInput = UserInput.with(
                userRequest.username(),
                userRequest.password(),
                userRequest.name(),
                userRequest.email(),
                userRequest.phone()
        );
        var documentInput = DocumentInput.with(documentRequest.value(), documentRequest.documentType());
        var createCustomerInput = CreateCustomerInput.with(
                userInput, documentInput, request.dateOfBirthday()
        );

        CreateCustomerOutput output = this.createCustomerUseCase.execute(createCustomerInput);
        return ResponseEntity.ok(new CreateCustomerResponse(output.id()));
    }
}