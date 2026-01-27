package br.com.terracota.domain.exception;

import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;

public class CraftsmanNotFoundException extends EntityNotFoundException{

    public CraftsmanNotFoundException(final ExceptionType exceptionType, final ErrorCode errorCode) {
        super(exceptionType, errorCode);
    }
}
