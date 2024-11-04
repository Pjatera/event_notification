package ru.javacourse.eventmanagement.service.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javacourse.eventmanagement.web.security.UserInfo;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;

    public UserInfo getCurrentUserAuthenticated() {
        var authentication = getAuthentication();
        return (UserInfo) authentication.getPrincipal();
    }

    public Collection<? extends GrantedAuthority> getAuthoritiesCurrentUserAuthenticated() {
        var authentication = getAuthentication();
        return authentication.getAuthorities();
    }

    private Authentication getAuthentication() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.error("Authentication required");
            throw new BadCredentialsException("Authentication required");
        }
        return authentication;
    }
}
