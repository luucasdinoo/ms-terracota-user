package br.com.terracota.application.usecase.get;

import br.com.terracota.application.UseCase;
import br.com.terracota.application.dto.output.UserOutputWithoutAddress;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.User;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchUsersUseCase extends UseCase<SearchFilter, Pagination<UserOutputWithoutAddress>> {

    private final UserGateway userGateway;

    @Override
    public Pagination<UserOutputWithoutAddress> execute(final SearchFilter filter) {
        Pagination<User> userPagination = this.userGateway.search(filter);
        return userPagination.map(UserOutputWithoutAddress::with);
    }
}
