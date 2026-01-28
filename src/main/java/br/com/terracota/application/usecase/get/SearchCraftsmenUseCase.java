package br.com.terracota.application.usecase.get;

import br.com.terracota.application.UseCase;
import br.com.terracota.application.dto.output.CraftsmanOutput;
import br.com.terracota.domain.gateway.CraftsmanGateway;
import br.com.terracota.domain.model.Craftsman;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchCraftsmenUseCase extends UseCase<SearchFilter, Pagination<CraftsmanOutput>> {

    private final CraftsmanGateway craftsmanGateway;

    @Override
    public Pagination<CraftsmanOutput> execute(final SearchFilter filter) {
        Pagination<Craftsman> userPagination = this.craftsmanGateway.search(filter);
        return userPagination.map(CraftsmanOutput::with);
    }
}
