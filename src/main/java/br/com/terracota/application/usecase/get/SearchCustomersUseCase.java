package br.com.terracota.application.usecase.get;

import br.com.terracota.application.UseCase;
import br.com.terracota.application.dto.output.CustomerOutput;
import br.com.terracota.domain.gateway.CustomerGateway;
import br.com.terracota.domain.model.Customer;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchCustomersUseCase extends UseCase<SearchFilter, Pagination<CustomerOutput>> {

    private final CustomerGateway customerGateway;

    @Override
    public Pagination<CustomerOutput> execute(final SearchFilter filter) {
        Pagination<Customer> userPagination = this.customerGateway.search(filter);
        return userPagination.map(CustomerOutput::with);
    }
}
