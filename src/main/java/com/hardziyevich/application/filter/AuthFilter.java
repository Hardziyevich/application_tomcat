package com.hardziyevich.application.filter;


import com.hardziyevich.application.service.dto.LoginUserDto;
import com.hardziyevich.application.service.dto.UserDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import static com.hardziyevich.application.servlet.ConstantServlet.UrlPath.LOGIN_PATH;
import static com.hardziyevich.application.servlet.ConstantServlet.UrlPath.REGISTRATION_PATH;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN_PATH,REGISTRATION_PATH);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if(isPublicPath(requestURI) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            String prevPage = ((HttpServletResponse) servletResponse).getHeader("referer");
            String page = prevPage != null ? prevPage : LOGIN_PATH;
            ((HttpServletResponse)servletResponse).sendRedirect(page);
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        //TODO find all users in session
        LoginUserDto user = (LoginUserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isPublicPath(String requestURI) {
        return PUBLIC_PATH.stream().anyMatch(requestURI::startsWith);
    }

}
