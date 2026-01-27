package br.com.terracota.infra.model;

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
    @Column(name = "USER_ID")
    private String id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ACTIVE")
    private Boolean active;

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
                getRoles().stream()
                        .map(RoleEntity::toDomain)
                        .collect(Collectors.toSet())
        );
    }
}



