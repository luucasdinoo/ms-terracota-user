package br.com.terracota.infra.api.controller;

import br.com.terracota.application.dto.input.CreateCraftsmanInput;
import br.com.terracota.application.dto.input.DocumentInput;
import br.com.terracota.application.dto.input.UpdateCraftsmanInput;
import br.com.terracota.application.dto.input.UserInput;
import br.com.terracota.application.dto.output.CraftsmanOutput;
import br.com.terracota.application.dto.output.CreateCraftsmanOutput;
import br.com.terracota.application.usecase.create.CreateCraftsmanUseCase;
import br.com.terracota.application.usecase.delete.DeleteCraftsmanUseCase;
import br.com.terracota.application.usecase.get.GetCraftsmanByDocumentUseCase;
import br.com.terracota.application.usecase.get.GetCraftsmanByIdUseCase;
import br.com.terracota.application.usecase.get.SearchCraftsmenUseCase;
import br.com.terracota.application.usecase.update.UpdateCraftsmanUseCase;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;
import br.com.terracota.infra.api.CraftsmanAPI;
import br.com.terracota.infra.api.dto.request.CreateCraftsmanRequest;
import br.com.terracota.infra.api.dto.request.DocumentRequest;
import br.com.terracota.infra.api.dto.request.UpdateCraftsmanRequest;
import br.com.terracota.infra.api.dto.request.UserRequest;
import br.com.terracota.infra.api.dto.response.CraftsmanResponse;
import br.com.terracota.infra.api.dto.response.CreateCraftsmanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CraftsmanController implements CraftsmanAPI {

    private final CreateCraftsmanUseCase createCraftsmanUseCase;
    private final GetCraftsmanByIdUseCase getCraftsmanByIdUseCase;
    private final GetCraftsmanByDocumentUseCase getCraftsmanByDocumentUseCase;
    private final UpdateCraftsmanUseCase updateCraftsmanUseCase;
    private final DeleteCraftsmanUseCase deleteCraftsmanUseCase;
    private final SearchCraftsmenUseCase searchCraftsmenUseCase;

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
        return ResponseEntity.created(URI.create("api/v1/craftsmen/" + output.id()))
                .body(new CreateCraftsmanResponse(output.id()));
    }

    @Override
    public ResponseEntity<CraftsmanResponse> getById(final String id) {
        CraftsmanOutput craftsmanOutput = this.getCraftsmanByIdUseCase.execute(id);
        return ResponseEntity.ok(CraftsmanResponse.with(craftsmanOutput));
    }

    @Override
    public ResponseEntity<CraftsmanResponse> getByDocument(final String document) {
        CraftsmanOutput craftsmanOutput = this.getCraftsmanByDocumentUseCase.execute(document);
        return ResponseEntity.ok(CraftsmanResponse.with(craftsmanOutput));
    }

    @Override
    public ResponseEntity<Void> update(String id, UpdateCraftsmanRequest request) {
        var updateCustomerInput = UpdateCraftsmanInput.with(
                id,
                request.username(),
                request.email(),
                request.name(),
                request.phone()
        );
        this.updateCraftsmanUseCase.execute(updateCustomerInput);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(final String id) {
        this.deleteCraftsmanUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Pagination<CraftsmanResponse>> search(
            final String username, final String email, final String name, final String document,
            final int page, final int perPage, final String sort, final String dir) {
        var filter = SearchFilter.with(username, email, name, document, page, perPage, sort, dir);
        Pagination<CraftsmanOutput> outputList = this.searchCraftsmenUseCase.execute(filter);
        return ResponseEntity.ok(outputList.map(CraftsmanResponse::with));
    }
}