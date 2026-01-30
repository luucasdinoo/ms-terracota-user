package br.com.terracota.infra.api.controller;

import br.com.terracota.application.dto.input.CreateCustomerInput;
import br.com.terracota.application.dto.input.DocumentInput;
import br.com.terracota.application.dto.input.UpdateCustomerInput;
import br.com.terracota.application.dto.input.UserInput;
import br.com.terracota.application.dto.output.CreateCustomerOutput;
import br.com.terracota.application.dto.output.CustomerOutput;
import br.com.terracota.application.usecase.create.CreateCustomerUseCase;
import br.com.terracota.application.usecase.delete.DeleteCustomerUseCase;
import br.com.terracota.application.usecase.get.GetCustomerByDocumentUseCase;
import br.com.terracota.application.usecase.get.GetCustomerByIdUseCase;
import br.com.terracota.application.usecase.get.SearchCustomersUseCase;
import br.com.terracota.application.usecase.update.UpdateCustomerUseCase;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;
import br.com.terracota.infra.api.CustomerAPI;
import br.com.terracota.infra.api.dto.request.CreateCustomerRequest;
import br.com.terracota.infra.api.dto.request.DocumentRequest;
import br.com.terracota.infra.api.dto.request.UpdateCustomerRequest;
import br.com.terracota.infra.api.dto.request.UserRequest;
import br.com.terracota.infra.api.dto.response.CreateCustomerResponse;
import br.com.terracota.infra.api.dto.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerAPI {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final GetCustomerByDocumentUseCase getCustomerByDocumentUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final SearchCustomersUseCase searchCustomersUseCase;

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
                userInput, documentInput, request.dateOfBirthday(), userRequest.userType()
        );

        CreateCustomerOutput output = this.createCustomerUseCase.execute(createCustomerInput);
        return ResponseEntity.created(URI.create("api/v1/customers/" + output.id()))
                .body(new CreateCustomerResponse(output.id()));
    }

    @Override
    public ResponseEntity<CustomerResponse> getById(final String id) {
        CustomerOutput customerOutput = this.getCustomerByIdUseCase.execute(id);
        return ResponseEntity.ok(CustomerResponse.with(customerOutput));
    }

    @Override
    public ResponseEntity<CustomerResponse> getByDocument(final String document) {
        CustomerOutput customerOutput = this.getCustomerByDocumentUseCase.execute(document);
        return ResponseEntity.ok(CustomerResponse.with(customerOutput));
    }

    @Override
    public ResponseEntity<Void> update(final String id, final UpdateCustomerRequest request) {
        var updateCustomerInput = UpdateCustomerInput.with(
                id,
                request.username(),
                request.email(),
                request.name(),
                request.phone()
        );
        this.updateCustomerUseCase.execute(updateCustomerInput);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(final String id) {
        this.deleteCustomerUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Pagination<CustomerResponse>> search(
            final String username, final String email, final String name, final String document,
            final int page, final int perPage, final String sort, final String dir) {
        var filter = SearchFilter.with(username, email, name, document, page, perPage, sort, dir);
        Pagination<CustomerOutput> outputList = this.searchCustomersUseCase.execute(filter);
        return ResponseEntity.ok(outputList.map(CustomerResponse::with));
    }
}