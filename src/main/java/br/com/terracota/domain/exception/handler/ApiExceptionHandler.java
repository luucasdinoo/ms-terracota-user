package br.com.terracota.domain.exception.handler;

import br.com.terracota.domain.enums.ErrorCode;
import br.com.terracota.domain.enums.ExceptionType;
import br.com.terracota.domain.exception.AlreadyExistsException;
import br.com.terracota.domain.exception.EntityNotFoundException;
import br.com.terracota.infra.api.dto.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        ex.printStackTrace();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        var response = ExceptionResponse.build(
                status.value(),
                ExceptionType.INTERNAL_SERVER_ERROR.getTitle(),
                ErrorCode.ECIS01.getCode(),
                ErrorCode.ECIS01.getMessage(),
                LocalDateTime.now(),
                null
        );

        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request)
    {
        var response = ExceptionResponse.build(
                status.value(),
                ExceptionType.INVALID_PARAMETER.getTitle(),
                ErrorCode.ECIP01.getCode(),
                ErrorCode.ECIP01.getMessage(),
                LocalDateTime.now(),
                getFieldErrors(ex.getFieldErrors())
        );

        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleAlreadyExistsException(AlreadyExistsException ex, WebRequest request) {
        var response = ExceptionResponse.build(
                ex.getStatus().value(),
                ex.getExceptionType().getTitle(),
                ex.getErrorCode().getCode(),
                ex.getErrorCode().getMessage(),
                LocalDateTime.now(),
                null
        );

        return handleExceptionInternal(ex, response, new HttpHeaders(), ex.getStatus(), request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        var response = ExceptionResponse.build(
                ex.getStatus().value(),
                ex.getExceptionType().getTitle(),
                ex.getErrorCode().getCode(),
                ex.getErrorCode().getMessage(),
                LocalDateTime.now(),
                null
        );

        return handleExceptionInternal(ex, response, new HttpHeaders(), ex.getStatus(), request);
    }

    private List<ExceptionResponse.Field> getFieldErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(error -> new ExceptionResponse.Field(error.getField(), error.getDefaultMessage()))
                .toList();
    }
}
