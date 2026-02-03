package br.com.terracota.domain.model;

import br.com.terracota.domain.enums.TypeUser;
import br.com.terracota.domain.utils.IdUtils;
import lombok.*;

import java.util.*;

@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private String id;

    private String username;

    private String password;

    private String name;

    private String email;

    private String phone;

    private Boolean active;

    private TypeUser userType;

    private List<Address> addresses;

    private Set<Role> roles;

    public Set<Role> getRoles() {
        return this.roles != null ? Collections.unmodifiableSet(this.roles) : Collections.emptySet();
    }

    public static User create(
            final String username,
            final String password,
            final String name,
            final String email,
            final String phone,
            final TypeUser userType,
            final List<Address> addresses,
            final Set<Role> roles
    ) {
        return new User(IdUtils.uuid(), username, password, name, email, phone, Boolean.TRUE, userType, addresses, roles);
    }

    public static User with(
            final String id,
            final String username,
            final String password,
            final String name,
            final String email,
            final String phone,
            final Boolean active,
            final TypeUser userType,
            final List<Address> addresses,
            final Set<Role> roles
    ) {
        return new User(id, username, password, name, email, phone, active, userType, addresses, roles);
    }

    public static User with(
            final String id,
            final String username,
            final String password,
            final String name,
            final String email,
            final String phone,
            final Boolean active,
            final TypeUser userType,
            final Set<Role> roles
    ) {
        return new User(id, username, password, name, email, phone, active,userType, null, roles);
    }

    public static User with(final User user) {
        return new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getActive(),
                user.getUserType(),
                new ArrayList<>(user.getAddresses()),
                new HashSet<>(user.getRoles())
        );
    }

    public User update(
            final String username,
            final String email,
            final String name,
            final String phone
    ) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.phone = phone;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
