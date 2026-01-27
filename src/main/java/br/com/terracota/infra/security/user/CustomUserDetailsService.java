package br.com.terracota.infra.security.user;

import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;
import br.com.terracota.domain.exception.EntityNotFoundException;
import br.com.terracota.domain.gateway.UserGateway;
import br.com.terracota.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserGateway userGateway;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = this.userGateway.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.NOT_FOUND, ErrorCode.ECNF03));
        return CustomUserDetails.build(user);
    }
}
