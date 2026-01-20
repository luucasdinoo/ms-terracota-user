package br.com.terracota.infra.model;

import br.com.terracota.domain.model.Craftsman;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity(name = "CRAFTSMAN")
@Table(name = "TRC_CRAFTSMAN")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class CraftsmanEntity {

    @Id
    @Column(name = "CRAFTSMAN_ID")
    private String id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "DOCUMENT_ID")
    private DocumentEntity document;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "CREATED_AT")
    private Instant createdAt;

    @Column(name = "UPDATED_AT")
    private Instant updatedAt;

    public static CraftsmanEntity from(final Craftsman craftsman){
        return new CraftsmanEntity(
                craftsman.getId(),
                craftsman.getUser().map(UserEntity::from).orElse(null),
                craftsman.getDocument().map(DocumentEntity::from).orElse(null),
                craftsman.getDateOfBirth(),
                craftsman.getCreatedAt(),
                craftsman.getUpdatedAt()
        );
    }

    public Craftsman toDomain(){
        return Craftsman.with(
                getId(),
                getUser().toDomain(),
                getDocument().toDomain(),
                getDateOfBirth(),
                getCreatedAt(),
                getUpdatedAt()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CraftsmanEntity that = (CraftsmanEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
