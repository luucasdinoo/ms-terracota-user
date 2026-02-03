package br.com.terracota.domain.enums;

import lombok.Getter;

@Getter
public enum TypeUser {

    CUSTOMER("Customer"),
    CRAFTSMAN("Craftsman"),
    ENTERPRISE("Enterprise"),
    ADMIN("Admin"),
    USER("User");

    private String description;

    TypeUser(final String description) {
        this.description = description;
    }
}
