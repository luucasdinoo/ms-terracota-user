package br.com.terracota.application.usecase.get;

import br.com.terracota.application.dto.output.CraftsmanOutput;
import br.com.terracota.domain.gateway.CraftsmanGateway;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.terracota.mock.TestMocks.SEARCH_CRAFTSMAN_PAGINATION;
import static br.com.terracota.mock.TestMocks.SEARCH_FILTER_TEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchCraftsmenUseCaseTest {

    @Mock
    private CraftsmanGateway craftsmanGateway;

    @InjectMocks
    private SearchCraftsmenUseCase useCase;

    @Test
    @DisplayName("Given filter when execute then returns craftsman output pagination")
    void givenFilter_WhenExecute_ThenReturnsCraftsmanOutputPagination(){
        when(this.craftsmanGateway.search(any(SearchFilter.class)))
                .thenReturn(SEARCH_CRAFTSMAN_PAGINATION);

        Pagination<CraftsmanOutput> output = this.useCase.execute(SEARCH_FILTER_TEST);

        assertThat(output).isNotNull();
        assertThat(output.items().getFirst().user()).isNotNull();
        assertThat(output.items().getFirst().document()).isNotNull();
        assertThat(output.items().getFirst().dateOfBirthday()).isNotNull();
        assertThat(output.items()).hasSize(3);
    }
}
