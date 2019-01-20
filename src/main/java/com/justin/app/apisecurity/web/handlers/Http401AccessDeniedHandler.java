package com.justin.app.apisecurity.web.handlers;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Http401AccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(final HttpServletRequest request, final HttpServletResponse response, final AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if(!response.isCommitted()){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
        else{
            response.setStatus(401);
        }
    }
}
