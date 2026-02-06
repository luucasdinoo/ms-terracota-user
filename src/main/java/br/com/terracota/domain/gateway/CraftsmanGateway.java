package br.com.terracota.domain.gateway;

import br.com.terracota.domain.model.Craftsman;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;

import java.util.Optional;

public interface CraftsmanGateway {

    Craftsman create(Craftsman customer);

    Craftsman update(Craftsman customer);

    Optional<Craftsman> findById(String id);

    Optional<Craftsman> findByDocumentValue(String value);

    Optional<Craftsman> findByUserId(String userId);

    void delete(Craftsman customer);

    void deleteById(String id);

    boolean existsById(String id);

    boolean existsByIdAndUserId(String customerId, String userId);

    Pagination<Craftsman> search(SearchFilter filter);

}
