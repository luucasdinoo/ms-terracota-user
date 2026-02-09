package br.com.terracota.application.usecase.get;

import br.com.terracota.application.dto.output.CustomerOutput;
import br.com.terracota.domain.gateway.CustomerGateway;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.terracota.mock.UseCaseTestMocks.SEARCH_CUSTOMER_PAGINATION;
import static br.com.terracota.mock.UseCaseTestMocks.SEARCH_FILTER_TEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchCustomersUseCaseTest {

    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private SearchCustomersUseCase useCase;

    @Test
    @DisplayName("Given filter when execute then returns customer output pagination")
    void givenFilter_WhenExecute_ThenReturnsCustomerOutputPagination(){
        when(this.customerGateway.search(any(SearchFilter.class)))
                .thenReturn(SEARCH_CUSTOMER_PAGINATION);

        Pagination<CustomerOutput> output = this.useCase.execute(SEARCH_FILTER_TEST);

        assertThat(output).isNotNull();
        assertThat(output.items().getFirst().user()).isNotNull();
        assertThat(output.items().getFirst().document()).isNotNull();
        assertThat(output.items().getFirst().dateOfBirthday()).isNotNull();
        assertThat(output.items()).hasSize(3);
    }
}
