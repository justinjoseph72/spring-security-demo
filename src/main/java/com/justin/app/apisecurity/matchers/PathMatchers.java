package com.justin.app.apisecurity.matchers;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class PathMatchers {

    private AntPathRequestMatcher matcher;

    public PathMatchers(String pattern){
        matcher = new AntPathRequestMatcher(pattern);
    }

    public Boolean matches(final HttpServletRequest request){
        return matcher.matches(request);
    }
}
