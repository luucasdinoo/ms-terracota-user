package br.com.terracota.infra;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DocumentValidator.class)
@Target({
        ElementType.FIELD,
        ElementType.RECORD_COMPONENT
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDocument {

    String message() default "Invalid document";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
