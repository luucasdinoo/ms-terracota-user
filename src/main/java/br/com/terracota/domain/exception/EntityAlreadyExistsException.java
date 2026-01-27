package br.com.terracota.domain.exception;

import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityAlreadyExistsException extends RuntimeException{

    private ExceptionType exceptionType;
    private ErrorCode errorCode;
    private HttpStatus status = HttpStatus.CONFLICT;

    public EntityAlreadyExistsException(final ExceptionType exceptionType, final ErrorCode errorCode) {
        this.exceptionType = exceptionType;
        this.errorCode = errorCode;
    }
}
