package br.com.terracota.infra.repository;

import br.com.terracota.infra.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String>, JpaSpecificationExecutor<CustomerEntity> {

    Optional<CustomerEntity> findByDocumentValue(String value);

    boolean existsByIdAndUserId(String customerId, String userId);
}
