package br.com.terracota.infra.security.user;

import br.com.terracota.domain.model.User;
import br.com.terracota.domain.utils.IdUtils;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomUserDetails implements UserDetails {

    private String id;
    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities;

    public static UserDetails build(final User user){
        return new CustomUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getDescription()))
                        .collect(Collectors.toList())
        );
    }

    public static UserDetails build(final String username, final String password, final Collection<GrantedAuthority> authorities){
        return new CustomUserDetails(
                IdUtils.uuid(),
                username,
                password,
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
