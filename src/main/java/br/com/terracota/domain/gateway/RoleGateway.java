package br.com.terracota.domain.gateway;

import br.com.terracota.domain.model.Role;

import java.util.Optional;

public interface RoleGateway {

    Role create(Role customer);

    Role update(Role customer);

    Optional<Role> findByDescription(String description);

    void deleteById(String id);
}
