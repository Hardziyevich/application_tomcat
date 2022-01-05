package com.hardziyevich.application.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebFilter("/command*")
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
