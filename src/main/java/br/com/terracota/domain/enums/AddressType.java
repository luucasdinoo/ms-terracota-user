package br.com.terracota.domain.enums;

import lombok.Getter;

@Getter
public enum AddressType {
    BILLING("Billing"),
    DELIVERY("Delivery");

    private String description;

    AddressType(String description) {
        this.description = description;
    }
}
