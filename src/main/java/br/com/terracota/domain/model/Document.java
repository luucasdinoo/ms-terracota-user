package br.com.terracota.domain.model;

import br.com.terracota.domain.enums.DocumentType;
import br.com.terracota.domain.utils.IdUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Document {

    private String id;

    private String value;

    private DocumentType documentType;

    public static Document create(final String value, final DocumentType documentType) {
        return new Document(IdUtils.uuid(), value, documentType);
    }

    public static Document with(final String id, final String document, final DocumentType documentType) {
        return new Document(id, document, documentType);
    }

    public static Document with(final Document document) {
        return new Document(document.getId(), document.getValue(), document.getDocumentType());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Document document1 = (Document) o;
        return Objects.equals(getId(), document1.getId()) && Objects.equals(getValue(), document1.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue());
    }
}
