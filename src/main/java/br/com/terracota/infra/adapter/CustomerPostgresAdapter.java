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
    public void deleteById(final String id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        }
    }

    private Customer save(final Customer customer) {
        return this.repository.save(CustomerEntity.from(customer)).toDomain();
    }
}
