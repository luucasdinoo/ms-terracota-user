package br.com.terracota.infra.api.controller;

import br.com.terracota.application.dto.input.CreateUserInput;
import br.com.terracota.application.dto.output.CreateUserOutput;
import br.com.terracota.application.dto.output.UserOutput;
import br.com.terracota.application.usecase.create.CreateUserByIdUseCase;
import br.com.terracota.application.usecase.get.GetUserByIdUseCase;
import br.com.terracota.infra.api.UserAPI;
import br.com.terracota.infra.api.dto.request.CreateUserRequest;
import br.com.terracota.infra.api.dto.response.CreateUserResponse;
import br.com.terracota.infra.api.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController implements UserAPI {

    private final CreateUserByIdUseCase createUserByIdUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;

    @Override
    public ResponseEntity<CreateUserResponse> create(final CreateUserRequest request) {
        var createUserInput = CreateUserInput.with(
                request.username(),
                request.password(),
                request.name(),
                request.email(),
                request.phone(),
                request.roles()
        );
        CreateUserOutput output = this.createUserByIdUseCase.execute(createUserInput);
        return ResponseEntity.created(URI.create("api/v1/users/" + output.id()))
                .body(new CreateUserResponse(output.id()));
    }

    @Override
    public ResponseEntity<UserResponse> getById(final String id) {
        UserOutput output = this.getUserByIdUseCase.execute(id);
        return ResponseEntity.ok(UserResponse.with(output));
    }
}
