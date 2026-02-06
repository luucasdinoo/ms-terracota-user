package br.com.terracota.application.usecase.get;

import br.com.terracota.application.dto.output.UserOutput;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.exception.UserNotFoundException;
import br.com.terracota.domain.gateway.UserGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static br.com.terracota.mock.TestMocks.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserByIdUseCaseTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private GetUserByIdUseCase useCase;

    @Test
    @DisplayName("Given a valid id when execute then return user output")
    void givenValidId_WhenExecute_ThenReturnUserOutput(){
        when(this.userGateway.findById(anyString()))
                .thenReturn(Optional.of(USER_TEST));

        UserOutput output = this.useCase.execute(USER_ID);

        assertThat(output).isNotNull();
        assertThat(output.id()).isEqualTo(USER_TEST.getId());
        assertThat(output.username()).isEqualTo(USER_TEST.getUsername());
        assertThat(output.email()).isEqualTo(USER_TEST.getEmail());
        assertThat(output.phone()).isEqualTo(USER_TEST.getPhone());
        assertThat(output.userType()).isEqualTo(USER_TEST.getUserType().getDescription());
    }

    @Test
    @DisplayName("Given a invalid id when execute then throw UserNotFoundException")
    void givenInvalidId_WhenExecute_ThenThrowUserNotFoundException(){
        when(this.userGateway.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.useCase.execute(USER_ID))
                .isInstanceOf(UserNotFoundException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECNF03, HttpStatus.NOT_FOUND);
    }
}
