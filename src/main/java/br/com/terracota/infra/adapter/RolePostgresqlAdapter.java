package br.com.terracota.infra.adapter;

import br.com.terracota.domain.gateway.RoleGateway;
import br.com.terracota.domain.model.Role;
import br.com.terracota.infra.model.RoleEntity;
import br.com.terracota.infra.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RolePostgresqlAdapter implements RoleGateway {

    private final RoleRepository repository;

    @Override
    public Role create(final Role role) {
        return save(role);
    }

    @Override
    public Role update(final Role role) {
        return save(role);
    }

    @Override
    public Optional<Role> findByDescription(final String description) {
        return this.repository.findByDescription(description)
                .map(RoleEntity::toDomain);
    }

    @Override
    public void deleteById(String id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        }
    }

    private Role save(final Role role) {
        return this.repository.save(RoleEntity.from(role)).toDomain();
    }
}
