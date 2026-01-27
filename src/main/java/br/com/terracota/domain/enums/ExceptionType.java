package br.com.terracota.domain.enums;

import lombok.Getter;

@Getter
public enum ExceptionType {

    INTERNAL_SERVER_ERROR("Internal Server Error"),
    BAD_REQUEST("Bad Request"),
    UNPROCESSABLE_ENTITY("Unprocessable Entity"),
    INVALID_PARAMETER("Invalid Parameter"),
    ENTITY_NOT_FOUND("Entity Not Found"),
    BUSINESS_ERROR("Business Error"),
    AUTHENTICATION_ERROR("Authentication Error"),
    AUTHORIZATION_ERROR("Authorization Error"),
    ALREADY_EXISTS("Already Exists"),
    RESOURCE_NOT_FOUND("Resource Not Found");

    private String title;

    ExceptionType(final String title) {
        this.title = title;
    }
}
