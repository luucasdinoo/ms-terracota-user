package br.com.terracota.infra.repository;

import br.com.terracota.infra.model.CraftsmanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CraftsmanRepository extends JpaRepository<CraftsmanEntity, String> {
}
