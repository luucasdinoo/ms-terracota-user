package br.com.terracota.infra.service;

import br.com.terracota.domain.gateway.CraftsmanGateway;
import br.com.terracota.domain.gateway.CustomerGateway;
import br.com.terracota.infra.security.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final CustomerGateway customerGateway;
    private final CraftsmanGateway craftsmanGateway;

    public boolean isCustomerAuthenticated(String customerId, Authentication authentication){
        var userDetails = (CustomUserDetails) authentication.getPrincipal();
        String authenticatedUserId = userDetails.getId();
        return this.customerGateway.existsByIdAndUserId(customerId, authenticatedUserId);
    }

    public boolean isCraftsmanAuthenticated(String craftsmanId, Authentication authentication){
        var userDetails = (CustomUserDetails) authentication.getPrincipal();
        String authenticatedUserId = userDetails.getId();
        return this.craftsmanGateway.existsByIdAndUserId(craftsmanId, authenticatedUserId);
    }
}
