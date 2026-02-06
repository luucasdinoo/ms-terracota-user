package br.com.terracota.domain.enums;

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
    public String getDescription() {
        return this.description.toUpperCase();
    }
}
