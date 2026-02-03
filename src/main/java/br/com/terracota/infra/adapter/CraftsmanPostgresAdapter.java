package br.com.terracota.infra.adapter;

import br.com.terracota.domain.gateway.CraftsmanGateway;
import br.com.terracota.domain.model.Craftsman;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;
import br.com.terracota.infra.model.CraftsmanEntity;
import br.com.terracota.infra.repository.CraftsmanRepository;
import br.com.terracota.infra.repository.specs.UserSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CraftsmanPostgresAdapter implements CraftsmanGateway {

    private final CraftsmanRepository repository;

    @Override
    public Craftsman create(final Craftsman craftsman) {
        return save(craftsman);
    }

    @Override
    public Craftsman update(final Craftsman customer) {
        return save(customer);
    }

    @Override
    public Optional<Craftsman> findById(final String id) {
        return this.repository.findById(id)
                .map(CraftsmanEntity::toDomain);
    }

    @Override
    public Optional<Craftsman> findByDocumentValue(final String value) {
        return this.repository.findByDocumentValue(value)
                .map(CraftsmanEntity::toDomain);
    }

    @Override
    public Optional<Craftsman> findByUserId(String userId) {
        return this.repository.findByUserId(userId)
                .map(CraftsmanEntity::toDomain);
    }

    @Override
    public void delete(final Craftsman customer) {
        this.repository.delete(CraftsmanEntity.from(customer));
    }

    @Override
    public void deleteById(final String id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        }
    }

    @Override
    public boolean existsById(String id) {
        return this.repository.existsById(id);
    }

    @Override
    public boolean existsByIdAndUserId(String craftsmanId, String userId) {
        return this.repository.existsByIdAndUserId(craftsmanId, userId);
    }

    @Override
    public Pagination<Craftsman> search(final SearchFilter filter) {
        var pageRequest = PageRequest.of(
                filter.page(),
                filter.perPage(),
                Sort.by(Sort.Direction.fromString(filter.dir()), "user." + filter.sort())
        );

        Page<CraftsmanEntity> page = this.repository.findAll(UserSpecs.withGenericFilter(filter), pageRequest);

        return new Pagination<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.map(CraftsmanEntity::toDomain).toList()
        );
    }

    private Craftsman save(final Craftsman craftsman) {
        return this.repository.save(CraftsmanEntity.from(craftsman)).toDomain();
    }
}
