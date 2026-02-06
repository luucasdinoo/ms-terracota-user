package br.com.terracota.application.usecase.get;

import br.com.terracota.application.dto.output.UserOutputWithoutAddress;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.terracota.mock.TestMocks.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchUsersUseCaseTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private SearchUsersUseCase useCase;

    @Test
    @DisplayName("Given filter when execute then returns user output pagination")
    void givenFilter_WhenExecute_ThenReturnsUserOutputPagination(){
        when(this.userGateway.search(any(SearchFilter.class)))
                .thenReturn(SEARCH_USER_PAGINATION);

        Pagination<UserOutputWithoutAddress> output = this.useCase.execute(SEARCH_FILTER_TEST);

        assertThat(output).isNotNull();
        assertThat(output.items()).hasSize(3);
        assertThat(output.items().getFirst().username()).isEqualTo(USER_TEST.getUsername());
        assertThat(output.items().getFirst().email()).isEqualTo(USER_TEST.getEmail());
        assertThat(output.items().getFirst().userType()).isEqualTo(USER_TEST.getUserType().getDescription());
    }
}
