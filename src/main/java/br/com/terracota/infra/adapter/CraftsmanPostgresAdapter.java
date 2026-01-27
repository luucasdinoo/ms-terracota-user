package br.com.terracota.infra.adapter;

import br.com.terracota.domain.gateway.CraftsmanGateway;
import br.com.terracota.domain.model.Craftsman;
import br.com.terracota.infra.model.CraftsmanEntity;
import br.com.terracota.infra.repository.CraftsmanRepository;
import lombok.RequiredArgsConstructor;
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

    private Craftsman save(final Craftsman craftsman) {
        return this.repository.save(CraftsmanEntity.from(craftsman)).toDomain();
    }
}
