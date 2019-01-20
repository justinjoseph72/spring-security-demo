package com.justin.app.apisecurity.web.filters;

import com.justin.app.apisecurity.web.authentication.ApiAuthentication;
import com.justin.app.apisecurity.web.handlers.Http401AccessDeniedHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiRequestFilter extends OncePerRequestFilter {

    private final AccessDeniedHandler accessDeniedHandler = new Http401AccessDeniedHandler();

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        String fetToken = request.getHeader("fe_token");
        String apiKey = request.getHeader("api_key");
        boolean hasAtleastOneAuthHeader = false;

        List<GrantedAuthority> authorities = new ArrayList<>();
        if ( StringUtils.hasText(fetToken) ) {
            hasAtleastOneAuthHeader = true;
            authorities.add(createNewAuthority("FE_USER"));
        }
        if ( StringUtils.hasText(apiKey) ) {
            hasAtleastOneAuthHeader = true;
            authorities.add(createNewAuthority("SHARES_USER"));
        }
        if ( hasAtleastOneAuthHeader ) {
            ApiAuthentication apiAuthentication = new ApiAuthentication("test@Email.com", authorities);
            SecurityContextHolder.getContext().setAuthentication(apiAuthentication);
            filterChain.doFilter(request, response);
        } else {
            accessDeniedHandler.handle(request, response, new AccessDeniedException("no api header found"));
        }
    }

    private GrantedAuthority createNewAuthority(final String authorityName) {
        return new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return authorityName;
            }
        };
    }
}
