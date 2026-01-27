package br.com.terracota.application.usecase.get;

import br.com.terracota.application.UseCase;
import br.com.terracota.application.dto.output.CraftsmanOutput;
import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;
import br.com.terracota.domain.exception.CraftsmanNotFoundException;
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
                .orElseThrow(() -> new CraftsmanNotFoundException(ExceptionType.NOT_FOUND, ErrorCode.ECNF02));
        return CraftsmanOutput.with(craftsman);
    }
}
