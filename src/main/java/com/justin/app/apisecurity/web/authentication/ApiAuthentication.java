package com.justin.app.apisecurity.web.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class ApiAuthentication extends AbstractAuthenticationToken {

    private String email;

    public ApiAuthentication(String email, List<GrantedAuthority> authorities){
        super(authorities);
        this.email = email;
    }

    @Override
    public Object getCredentials() {
        return email;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }
}
