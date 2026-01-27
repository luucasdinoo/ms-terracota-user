package br.com.terracota.domain.enums;

import br.com.terracota.domain.model.Craftsman;
import br.com.terracota.domain.model.Customer;
import br.com.terracota.domain.model.Role;
import br.com.terracota.domain.model.User;
import lombok.Getter;

import static br.com.terracota.domain.utils.MessageUtils.*;

@Getter
public enum ErrorCode {
    ECNT01("ECNT01", ENTITY_NOT_FOUND_TEMPLATE, Customer.class),
    ECNT02("ECNT02", ENTITY_NOT_FOUND_TEMPLATE, Craftsman.class),
    ECNT03("ECNT03", ENTITY_NOT_FOUND_TEMPLATE, User.class),
    ECNT04("ECNT04", ENTITY_NOT_FOUND_TEMPLATE, Role.class),
    ECAE01("ECAE01", ENTITY_ALREADY_EXISTS_TEMPLATE),
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
