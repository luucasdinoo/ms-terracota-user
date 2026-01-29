package br.com.terracota.infra.service;

import br.com.terracota.domain.gateway.CraftsmanGateway;
import br.com.terracota.domain.gateway.CustomerGateway;
import br.com.terracota.domain.model.User;
import br.com.terracota.infra.security.user.CustomAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final CustomerGateway customerGateway;
    private final CraftsmanGateway craftsmanGateway;

    public boolean isCustomerAuthenticated(String customerId, Authentication authentication){
        var user = (User) authentication;
        String authenticatedUserId = user.getId();
        return this.customerGateway.existsByIdAndUserId(customerId, authenticatedUserId);
    }

    public boolean isCraftsmanAuthenticated(String craftsmanId, Authentication authentication){
        var user = (User) authentication;
        String authenticatedUserId = user.getId();
        return this.craftsmanGateway.existsByIdAndUserId(craftsmanId, authenticatedUserId);
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof CustomAuthentication customAuthentication){
            return customAuthentication.getUser();
        }
        return null;
    }
}
