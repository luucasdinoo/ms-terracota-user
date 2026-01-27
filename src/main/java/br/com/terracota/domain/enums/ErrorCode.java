package br.com.terracota.domain.enums;

import br.com.terracota.domain.model.Craftsman;
import br.com.terracota.domain.model.Customer;
import br.com.terracota.domain.model.Role;
import br.com.terracota.domain.model.User;
import lombok.Getter;

import static br.com.terracota.domain.utils.MessageUtils.*;

@Getter
public enum ErrorCode {
    ECNF01("ECNF01", ENTITY_NOT_FOUND_TEMPLATE, Customer.class),
    ECNF02("ECNF02", ENTITY_NOT_FOUND_TEMPLATE, Craftsman.class),
    ECNF03("ECNF03", ENTITY_NOT_FOUND_TEMPLATE, User.class),
    ECNF04("ECNF04", ENTITY_NOT_FOUND_TEMPLATE, Role.class),
    ECAE01("ECAE01", ENTITY_ALREADY_EXISTS_TEMPLATE),
    ECAE02("ECAE02", USERNAME_ALREADY_EXISTS_TEMPLATE),
    ECAE03("ECAE03", EMAIL_ALREADY_EXISTS_TEMPLATE),
    ECIS01("ECIS01", INTERNAL_SERVER_ERROR_TEMPLATE),
    ECIP01("ECIP01", INVALID_PARAMETER_TEMPLATE);

    private String code;
    private String messageTemplate;

    ErrorCode(final String code, final String messageTemplate, final Class<?> entityClass) {
        this.code = code;
        this.messageTemplate = messageTemplate.formatted(entityClass.getSimpleName());
    }

    ErrorCode(final String code, final String messageTemplate) {
        this.code = code;
        this.messageTemplate = messageTemplate;
    }

    public String getMessage(){
        return this.messageTemplate;
    }
}
