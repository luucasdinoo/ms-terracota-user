package br.com.terracota.application.usecase.get;

import br.com.terracota.application.dto.output.CraftsmanOutput;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.exception.CraftsmanNotFoundException;
import br.com.terracota.domain.gateway.CraftsmanGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static br.com.terracota.mock.UseCaseTestMocks.CRAFTSMAN_ID;
import static br.com.terracota.mock.UseCaseTestMocks.CRAFTSMAN_TEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCraftsmanByDocumentUseCaseTest {

    @Mock
    private CraftsmanGateway craftsmanGateway;

    @InjectMocks
    private GetCraftsmanByDocumentUseCase useCase;

    @Test
    @DisplayName("Given a valid document when execute then return craftsman output")
    void givenValidDocument_WhenExecute_ThenReturnUserOutput(){
        when(this.craftsmanGateway.findByDocumentValue(anyString()))
                .thenReturn(Optional.of(CRAFTSMAN_TEST));

        CraftsmanOutput output = this.useCase.execute(CRAFTSMAN_ID);

        assertThat(output).isNotNull();
        assertThat(output.user()).isNotNull();
        assertThat(output.document()).isNotNull();
        assertThat(output.createdAt()).isNotNull();
        assertThat(output.updatedAt()).isNotNull();
        assertThat(output.id()).isEqualTo(CRAFTSMAN_TEST.getId());
    }

    @Test
    @DisplayName("Given a invalid document when execute then throw CraftsmanNotFoundException")
    void givenInvalidDocument_WhenExecute_ThenThrowCraftsmanNotFoundException(){
        when(this.craftsmanGateway.findByDocumentValue(anyString()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.useCase.execute(CRAFTSMAN_ID))
                .isInstanceOf(CraftsmanNotFoundException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECNF02, HttpStatus.NOT_FOUND);
    }
}
