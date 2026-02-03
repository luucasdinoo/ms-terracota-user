package br.com.terracota.infra.repository;

import br.com.terracota.infra.model.CraftsmanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CraftsmanRepository extends JpaRepository<CraftsmanEntity, String>, JpaSpecificationExecutor<CraftsmanEntity> {

    Optional<CraftsmanEntity> findByDocumentValue(String value);

    Optional<CraftsmanEntity> findByUserId(String userId);

    boolean existsByIdAndUserId(String craftsmanId, String userId);
}
