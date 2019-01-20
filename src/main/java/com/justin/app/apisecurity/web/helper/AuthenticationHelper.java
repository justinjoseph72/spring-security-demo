package com.justin.app.apisecurity.web.helper;

import com.justin.app.apisecurity.matchers.PathMatchers;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class AuthenticationHelper {

    public static boolean canAuthenticateRequest(final HttpServletRequest request, final String[] whiteListPaths) {
        List<String> whiteList = Arrays.asList(whiteListPaths);
        boolean isRequestInWhiteList = whiteList.stream().map(pattern -> matchPattern(pattern, request)).reduce((a, b) -> a || b).get();
        return isRequestInWhiteList;
    }

    private static Boolean matchPattern(final String pattern, final HttpServletRequest request) {
        PathMatchers matcher = new PathMatchers(pattern);
        return matcher.matches(request);
    }
}
