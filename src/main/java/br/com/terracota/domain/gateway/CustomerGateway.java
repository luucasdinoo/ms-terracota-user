package br.com.terracota.domain.gateway;

import br.com.terracota.domain.model.Customer;

import java.util.Optional;

public interface CustomerGateway {

    Customer create(Customer customer);

    Customer update(Customer customer);

    Optional<Customer> findById(String id);

    void deleteById(String id);
}
