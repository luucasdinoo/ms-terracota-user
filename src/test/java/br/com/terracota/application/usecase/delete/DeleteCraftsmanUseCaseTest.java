package br.com.terracota.application.usecase.delete;

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

import static br.com.terracota.mock.UseCaseTestMocks.USER_ID;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteCraftsmanUseCaseTest {

    @Mock
    private CraftsmanGateway craftsmanGateway;

    @InjectMocks
    private DeleteCraftsmanUseCase useCase;

    @Test
    @DisplayName("Given a valid id when craftsman exists then return void")
    void givenValidId_WhenCraftsmanExists_ThenReturnVoid(){
        when(this.craftsmanGateway.existsById(anyString()))
                .thenReturn(Boolean.TRUE);

        this.useCase.execute(USER_ID);

        verify(this.craftsmanGateway, times(1)).deleteById(eq(USER_ID));
    }

    @Test
    @DisplayName("Given a invalid id when craftsman not found then throw CraftsmanNotFoundException")
    void givenInvalidId_WhenCraftsmanNotFound_ThenThrowCraftsmanNotFoundException(){
        when(this.craftsmanGateway.existsById(anyString()))
                .thenReturn(Boolean.FALSE);
        
        assertThatThrownBy(() -> this.useCase.execute(USER_ID))
                .isInstanceOf(CraftsmanNotFoundException.class)
                .extracting("errorCode", "status")
                .containsExactly(ErrorCode.ECNF02, HttpStatus.NOT_FOUND);

        verify(this.craftsmanGateway, never()).deleteById(eq(USER_ID));
    }
}
