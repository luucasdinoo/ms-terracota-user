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
public class GetCraftsmanByIdUseCase extends UseCase<String, CraftsmanOutput> {
    
    private final CraftsmanGateway craftsmanGateway;

    @Override
    public CraftsmanOutput execute(final String id) {
        Craftsman craftsman = this.craftsmanGateway.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        return CraftsmanOutput.with(craftsman);
    }
}
