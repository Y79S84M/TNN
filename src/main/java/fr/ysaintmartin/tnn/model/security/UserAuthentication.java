package fr.ysaintmartin.tnn.model.security;

import fr.ysaintmartin.tnn.entity.security.UserCredentials;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
public class UserAuthentication implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserAuthentication(UserCredentials userCredentials) {
        this.username = userCredentials.getUsername();
        this.password = userCredentials.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority(userCredentials.getRole().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
