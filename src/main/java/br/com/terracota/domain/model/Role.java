package br.com.terracota.domain.model;

import br.com.terracota.domain.utils.IdUtils;
import lombok.*;

import java.util.Objects;

@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {

    private String id;

    private String description;

    public static Role create(final String description) {
        return new Role(IdUtils.uuid(), description);
    }

    public static Role with(final String id, final String description) {
        return new Role(id, description);
    }

    public static Role with(final Role role) {
        return new Role(role.getId(), role.getDescription());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
