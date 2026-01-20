package br.com.terracota.infra.model;

import br.com.terracota.domain.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@Entity(name = "ROLE")
@Table(name = "TRC_ROLE")
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class RoleEntity {

    @Id
    @Column(name = "ROLE_ID", nullable = false, length = 32)
    private String id;

    @Column(name = "DESCRIPTION", nullable = false, length = 15)
    private String description;

    public static RoleEntity from(final Role role){
        return new RoleEntity(
                role.getId(),
                role.getDescription()
        );
    }

    public Role toDomain(){
        return Role.with(
                this.id,
                this.description
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
