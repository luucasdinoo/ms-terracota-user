package br.com.terracota.domain.gateway;

import br.com.terracota.domain.model.User;

import java.util.Optional;

public interface UserGateway {

    User create(User customer);

    User update(User customer);

    Optional<User> findById(String id);

    Optional<User> findByUsername(String id);

    Optional<User> findByEmail(String id);

    void deleteById(String id);
}
