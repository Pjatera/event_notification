package ru.javacourse.eventmanagement.web.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record UserInfo(Long id, Collection<? extends GrantedAuthority> authorities) {

}