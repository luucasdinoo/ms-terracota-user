package br.com.terracota.infra.model;

import br.com.terracota.domain.enums.TypeUser;
import br.com.terracota.domain.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "USER")
@Table(name = "TRC_USER")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class UserEntity {

    @Id
    @Column(name = "USER_ID", nullable = false, length = 32)
    private String id;

    @Column(name = "USERNAME", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 254)
    private String password;

    @Column(name = "NAME", nullable = false, length = 254, unique = true)
    private String name;

    @Column(name = "EMAIL", nullable = false, length = 150)
    private String email;

    @Column(name = "PHONE", length = 11)
    private String phone;

    @Column(name = "ACTIVE", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE", nullable = false, length = 15)
    private TypeUser userType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AddressEntity> addresses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TRC_USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", table = "TRC_USER"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID", table = "TRC_ROLE")
    )
    private Set<RoleEntity> roles;

    public static UserEntity from(final User user){
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getActive(),
                user.getUserType(),
                Optional.ofNullable(user.getAddresses())
                        .orElse(List.of())
                        .stream()
                        .map(AddressEntity::from)
                        .toList(),
                user.getRoles().stream()
                        .map(RoleEntity::from)
                        .collect(Collectors.toSet())
        );
    }

    public User toDomain(){
        return User.with(
                getId(),
                getUsername(),
                getPassword(),
                getName(),
                getEmail(),
                getPhone(),
                getActive(),
                getUserType(),
                getAddresses().stream()
                        .map(AddressEntity::toDomain)
                        .collect(Collectors.toList()),
                getRoles().stream()
                        .map(RoleEntity::toDomain)
                        .collect(Collectors.toSet())
        );
    }

    public User toDomainWithoutAddress(){
        return User.with(
                getId(),
                getUsername(),
                getPassword(),
                getName(),
                getEmail(),
                getPhone(),
                getActive(),
                getUserType(),
                getRoles().stream()
                        .map(RoleEntity::toDomain)
                        .collect(Collectors.toSet())
        );
    }
}



