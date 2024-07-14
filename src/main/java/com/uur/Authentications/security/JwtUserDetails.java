package com.uur.Authentications.security;

import com.uur.Authentications.entities.Role;
import com.uur.Authentications.entities.User;
import com.uur.Authentications.entities.UserAuthority;
import com.uur.Authentications.entities.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

    public int id;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(int id, String username, String password, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = authorities;
    }


    public static JwtUserDetails create(User user, List<UserRole> roles, List<UserAuthority> authorities) {
        List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>(roles.stream().map(userRole ->
                new SimpleGrantedAuthority("ROLE_" + userRole.getRole().getName().toUpperCase(Locale.ENGLISH))
        ).toList());
        authorities.forEach(userAuthority -> {
            authoritiesList.add(new SimpleGrantedAuthority(userAuthority.getAuthority().getName()));
        });
        return new JwtUserDetails(user.getId(), user.getUserName(), user.getPasswordHash(), true, !user.isLockoutEnabled(), true, true, authoritiesList);
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}