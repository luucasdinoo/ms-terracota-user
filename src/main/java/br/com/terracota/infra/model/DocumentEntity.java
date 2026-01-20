package br.com.terracota.infra.model;

import br.com.terracota.domain.enums.DocumentType;
import br.com.terracota.domain.model.Document;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity(name = "DOCUMENT")
@Table(name = "TRC_DOCUMENT")
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class DocumentEntity {

    @Id
    @Column(name = "DOCUMENT_ID")
    private String id;

    @Column(name = "VALUE")
    private String value;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    public static DocumentEntity from(final Document document){
        return new DocumentEntity(
                document.getId(),
                document.getValue(),
                document.getDocumentType()
        );
    }

    public Document toDomain(){
        return Document.with(
                this.id,
                this.value,
                this.documentType
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DocumentEntity that = (DocumentEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
