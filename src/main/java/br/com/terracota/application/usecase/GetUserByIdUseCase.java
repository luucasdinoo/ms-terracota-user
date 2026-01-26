package br.com.terracota.application.usecase;

import br.com.terracota.application.UseCase;
import br.com.terracota.application.dto.output.UserOutput;
import br.com.terracota.domain.exception.EntityNotFoundException;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserByIdUseCase extends UseCase<String, UserOutput> {
    
    private final UserGateway userGateway;

    @Override
    public UserOutput execute(final String id) {
        User user = this.userGateway.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        return UserOutput.with(user);
    }
}
