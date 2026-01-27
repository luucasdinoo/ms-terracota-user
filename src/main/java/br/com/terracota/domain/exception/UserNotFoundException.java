package br.com.terracota.domain.exception;

import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(final ExceptionType exceptionType, final ErrorCode errorCode) {
        super(exceptionType, errorCode);
    }
}
