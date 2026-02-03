package br.com.terracota.infra.api.controller;

import br.com.terracota.application.dto.input.CreateUserInput;
import br.com.terracota.application.dto.output.CreateUserOutput;
import br.com.terracota.application.dto.output.UserOutput;
import br.com.terracota.application.dto.output.UserOutputWithoutAddress;
import br.com.terracota.application.usecase.create.CreateUserUseCase;
import br.com.terracota.application.usecase.delete.DeleteUserUseCase;
import br.com.terracota.application.usecase.get.GetUserByIdUseCase;
import br.com.terracota.application.usecase.get.SearchUsersUseCase;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;
import br.com.terracota.infra.api.UserAPI;
import br.com.terracota.infra.api.dto.request.CreateUserRequest;
import br.com.terracota.infra.api.dto.response.CreateUserResponse;
import br.com.terracota.infra.api.dto.response.UserResponse;
import br.com.terracota.infra.api.dto.response.UserResponseWithoutAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController implements UserAPI {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final SearchUsersUseCase searchUsersUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @Override
    public ResponseEntity<CreateUserResponse> create(final CreateUserRequest request) {
        var createUserInput = CreateUserInput.with(
                request.username(),
                request.password(),
                request.name(),
                request.email(),
                request.phone(),
                request.userType(),
                request.roles()
        );
        CreateUserOutput output = this.createUserUseCase.execute(createUserInput);
        return ResponseEntity.created(URI.create("api/v1/users/" + output.id()))
                .body(new CreateUserResponse(output.id()));
    }

    @Override
    public ResponseEntity<UserResponse> getById(final String id) {
        UserOutput output = this.getUserByIdUseCase.execute(id);
        return ResponseEntity.ok(UserResponse.with(output));
    }

    @Override
    public ResponseEntity<Pagination<UserResponseWithoutAddress>> search(
            final String username, final String email, final String name, final String document,
            final int page, final int perPage, final String sort, final String dir
    ) {
        var filter = SearchFilter.with(username, email, name, document, page, perPage, sort, dir);
        Pagination<UserOutputWithoutAddress> outputList = this.searchUsersUseCase.execute(filter);
        return ResponseEntity.ok(outputList.map(UserResponseWithoutAddress::with));
    }

    @Override
    public ResponseEntity<Void> delete(final String id) {
        this.deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
