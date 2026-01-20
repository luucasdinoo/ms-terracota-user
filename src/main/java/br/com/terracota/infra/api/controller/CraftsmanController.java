package br.com.terracota.infra.api.controller;

import br.com.terracota.application.dto.input.CreateCraftsmanInput;
import br.com.terracota.application.dto.input.DocumentInput;
import br.com.terracota.application.dto.input.UserInput;
import br.com.terracota.application.dto.output.CreateCraftsmanOutput;
import br.com.terracota.application.usecase.CreateCraftsmanUseCase;
import br.com.terracota.infra.api.CraftsmanAPI;
import br.com.terracota.infra.dto.request.CreateCraftsmanRequest;
import br.com.terracota.infra.dto.request.DocumentRequest;
import br.com.terracota.infra.dto.request.UserRequest;
import br.com.terracota.infra.dto.response.CreateCraftsmanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CraftsmanController implements CraftsmanAPI {

    private final CreateCraftsmanUseCase createCraftsmanUseCase;

    @Override
    public ResponseEntity<CreateCraftsmanResponse> create(final CreateCraftsmanRequest request) {
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
        var createCraftsmanInput = CreateCraftsmanInput.with(
                userInput, documentInput, request.dateOfBirthday()
        );

        CreateCraftsmanOutput output = this.createCraftsmanUseCase.execute(createCraftsmanInput);
        return ResponseEntity.ok(new CreateCraftsmanResponse(output.id()));
    }
}