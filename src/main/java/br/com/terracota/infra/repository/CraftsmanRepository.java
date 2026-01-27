package br.com.terracota.infra.repository;

import br.com.terracota.infra.model.CraftsmanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CraftsmanRepository extends JpaRepository<CraftsmanEntity, String> {

    Optional<CraftsmanEntity> findByDocumentValue(String value);

    boolean existsByIdAndUserId(String craftsmanId, String userId);
}
