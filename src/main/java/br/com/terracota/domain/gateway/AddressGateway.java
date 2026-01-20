package br.com.terracota.domain.gateway;

import br.com.terracota.domain.model.Address;

import java.util.Optional;

public interface AddressGateway {

    Address create(Address customer);

    Address update(Address customer);

    Optional<Address> findById(String id);

    void deleteById(String id);
}
