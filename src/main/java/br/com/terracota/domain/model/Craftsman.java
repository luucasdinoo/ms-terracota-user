package br.com.terracota.domain.model;

import br.com.terracota.domain.utils.IdUtils;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Craftsman {

    private String id;

    private User user;

    private Document document;

    private Date dateOfBirth;

    private Instant createdAt;

    private Instant updatedAt;

    public static Craftsman create(
            final User user,
            final Document document,
            final Date dateOfBirth
    ) {
        return new Craftsman(IdUtils.uuid(), user, document, dateOfBirth, Instant.now(), Instant.now());
    }

    public static Craftsman with(
            final String id,
            final User user,
            final Document document,
            final Date dateOfBirth,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        return new Craftsman(id, user, document, dateOfBirth, createdAt, updatedAt);
    }

    public static Craftsman with(final Craftsman craftsman) {
        return new Craftsman(
                craftsman.getId(),
                craftsman.getUser().orElse(null),
                craftsman.getDocument().orElse(null),
                craftsman.getDateOfBirth(),
                craftsman.getCreatedAt(),
                craftsman.getUpdatedAt()
        );
    }

    public Optional<User> getUser() {
        return Optional.of(this.user);
    }

    public Optional<Document> getDocument() {
        return Optional.of(this.document);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Craftsman customer = (Craftsman) o;
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
