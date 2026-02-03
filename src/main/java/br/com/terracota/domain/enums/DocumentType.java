package br.com.terracota.domain.enums;

import lombok.Getter;

@Getter
public enum DocumentType {
    CPF("document"),
    CNPJ("cnpj");

    private String description;

    DocumentType(final String description) {
        this.description = description;
    }
}
