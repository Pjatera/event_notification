package ru.javacourse.event_notificator.web.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record UserInfo(Long id, Collection<? extends GrantedAuthority> authorities) implements UserDetails {


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return String.valueOf(id);
    }

    @Override
    public String getUsername() {
        return String.valueOf(id);
    }
}
