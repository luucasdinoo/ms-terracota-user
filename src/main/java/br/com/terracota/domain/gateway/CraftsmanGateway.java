package br.com.terracota.domain.gateway;

import br.com.terracota.domain.model.Craftsman;

import java.util.Optional;

public interface CraftsmanGateway {

    Craftsman create(Craftsman customer);

    Craftsman update(Craftsman customer);

    Optional<Craftsman> findById(String id);

    void deleteById(String id);
}
