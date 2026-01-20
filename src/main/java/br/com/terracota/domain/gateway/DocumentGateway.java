package br.com.terracota.domain.gateway;

import br.com.terracota.domain.model.Document;

import java.util.Optional;

public interface DocumentGateway {

    Document create(Document customer);

    Document update(Document customer);

    Optional<Document> findById(String id);

    void deleteById(String id);
}
