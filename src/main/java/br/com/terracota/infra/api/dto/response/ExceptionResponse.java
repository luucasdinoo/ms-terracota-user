package br.com.terracota.infra.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExceptionResponse(
        Integer status,
        String title,
        @JsonProperty("error_code") String errorCode,
        String message,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestamp,
        List<Field> fields
) {

    public static ExceptionResponse build(
            final Integer status,
            final String title,
            final String errorCode,
            final String message,
            final LocalDateTime timestamp,
            final List<Field> fields
    ) {
        return new ExceptionResponse(status, title, errorCode, message, timestamp, fields);
    }

    public record Field(
            String field,
            String message
    ) {}
}
