package br.com.terracota.application.usecase.get;

import br.com.terracota.application.UseCase;
import br.com.terracota.application.dto.output.CraftsmanOutput;
import br.com.terracota.domain.exception.EntityNotFoundException;
import br.com.terracota.domain.gateway.CraftsmanGateway;
import br.com.terracota.domain.model.Craftsman;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCraftsmanByDocumentUseCase extends UseCase<String, CraftsmanOutput> {
    
    private final CraftsmanGateway craftsmanGateway;

    @Override
    public CraftsmanOutput execute(final String document) {
        Craftsman customer = this.craftsmanGateway.findByDocumentValue(document)
                .orElseThrow(() -> new EntityNotFoundException());
        return CraftsmanOutput.with(customer);
    }
}
