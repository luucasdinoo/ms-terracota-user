package br.com.terracota.domain.exception;

import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private ExceptionType exceptionType;
    private ErrorCode errorCode;
    private HttpStatus status = HttpStatus.NOT_FOUND;

    public EntityNotFoundException (final ExceptionType exceptionType, final ErrorCode errorCode) {
        this.exceptionType = exceptionType;
        this.errorCode = errorCode;
    }
}
