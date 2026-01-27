package br.com.terracota.infra.adapter;

import br.com.terracota.domain.gateway.CustomerGateway;
import br.com.terracota.domain.model.Customer;
import br.com.terracota.infra.model.CustomerEntity;
import br.com.terracota.infra.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerPostgresAdapter implements CustomerGateway {

    private final CustomerRepository repository;

    @Override
    public Customer create(final Customer customer) {
        return save(customer);
    }

    @Override
    public Customer update(final Customer customer) {
        return save(customer);
    }

    @Override
    public Optional<Customer> findById(final String id) {
        return this.repository.findById(id)
                .map(CustomerEntity::toDomain);
    }

    @Override
    public Optional<Customer> findByDocumentValue(String value) {
        return this.repository.findByDocumentValue(value)
                .map(CustomerEntity::toDomain);
    }

    @Override
    public void deleteById(final String id) {
        this.repository.deleteById(id);
    }

    @Override
    public boolean existsById(final String id) {
        return this.repository.existsById(id);
    }

    @Override
    public boolean existsByIdAndUserId(String customerId, String userId) {
        return this.repository.existsByIdAndUserId(customerId, userId);
    }

    private Customer save(final Customer customer) {
        return this.repository.save(CustomerEntity.from(customer)).toDomain();
    }
}
