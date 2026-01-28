package br.com.terracota.domain.gateway;

import br.com.terracota.domain.model.User;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;

import java.util.Optional;

public interface UserGateway {

    User create(User user);

    User update(User user);

    Optional<User> findById(String id);

    Optional<User> findByUsername(String id);

    Optional<User> findByEmail(String id);

    void deleteById(String id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Pagination<User> search(SearchFilter filter);
}
