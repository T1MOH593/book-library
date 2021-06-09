package com.vlad.my_own_web_app.filter;

import com.vlad.my_own_web_app.util.UrlPath;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static com.vlad.my_own_web_app.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN_PATH, REGISTRATION_PATH);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            var prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage == null ? LOGIN_PATH : prevPage);
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        return Optional.ofNullable(((HttpServletRequest) servletRequest)
                .getSession().getAttribute("email")).isPresent();
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream()
                .anyMatch(uri::startsWith);
    }
}
