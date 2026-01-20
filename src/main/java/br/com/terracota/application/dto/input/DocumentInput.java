package br.com.terracota.application.dto.input;

public record DocumentInput(
        String value,
        String documentType
) {
    public static DocumentInput with(String value, String documentType){
        return new DocumentInput(value, documentType);
    }
}
