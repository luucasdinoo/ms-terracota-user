package br.com.terracota.infra;

import br.com.terracota.infra.api.dto.request.DocumentRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DocumentValidator implements ConstraintValidator<ValidDocument, DocumentRequest> {

    @Override
    public boolean isValid(final DocumentRequest request, ConstraintValidatorContext context) {
        String value = request.value();
        String type = request.documentType();

        if (type == null || type.isBlank()) {
            return violation(context, "documentType", "Document type must be CPF or CNPJ");
        }

        if (value == null || value.isBlank()) {
            return violation(context, "value", "Document value must not be blank");
        }

        if (!value.matches("\\d+")) {
            return violation(context, "value", "Document must contain only numbers");
        }

        return switch (type.toUpperCase()) {
            case "CPF" -> validateCPF(value, context);
            case "CNPJ" -> validateCNPJ(value, context);
            default -> violation(context, "documentType", "Document type must be CPF or CNPJ");
        };
    }

    private boolean validateCNPJ(String cnpj, ConstraintValidatorContext context) {
        if (cnpj.length() != 14 || !isValidCNPJ(cnpj)) {
            return violation(context, "value", "Invalid CNPJ");
        }
        return true;
    }

    private boolean isValidCNPJ(String cnpj) {
        if (cnpj.chars().distinct().count() == 1) return false;

        int[] w1 = {5,4,3,2,9,8,7,6,5,4,3,2};
        int[] w2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += (cnpj.charAt(i) - '0') * w1[i];
        }

        int d1 = sum % 11;
        d1 = d1 < 2 ? 0 : 11 - d1;

        sum = 0;
        for (int i = 0; i < 13; i++) {
            sum += (cnpj.charAt(i) - '0') * w2[i];
        }

        int d2 = sum % 11;
        d2 = d2 < 2 ? 0 : 11 - d2;

        return d1 == (cnpj.charAt(12) - '0')
                && d2 == (cnpj.charAt(13) - '0');
    }

    private boolean validateCPF(String cpf, ConstraintValidatorContext context) {
        if (cpf.length() != 11 || !isValidCPF(cpf)) {
            return violation(context, "value", "Invalid CPF");
        }
        return true;
    }

    private boolean isValidCPF(String cpf) {
        if (cpf.chars().distinct().count() == 1) return false;

        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < 9; i++) {
            int d = cpf.charAt(i) - '0';
            sum1 += d * (10 - i);
            sum2 += d * (11 - i);
        }

        int d1 = 11 - (sum1 % 11);
        d1 = d1 >= 10 ? 0 : d1;

        sum2 += d1 * 2;
        int d2 = 11 - (sum2 % 11);
        d2 = d2 >= 10 ? 0 : d2;

        return d1 == (cpf.charAt(9) - '0')
                && d2 == (cpf.charAt(10) - '0');
    }

    private boolean violation(
            ConstraintValidatorContext context,
            String field,
            String message
    ) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(field)
                .addConstraintViolation();
        return false;
    }
}
