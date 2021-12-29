//package com.hardziyevich.application.filter;
//
//
//import com.hardziyevich.application.service.dto.LoginUserDto;
//import com.hardziyevich.application.servlet.JspHelper;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.Set;
//
//import static com.hardziyevich.application.servlet.ConstantServlet.UrlPath.*;
//
//@WebFilter("/*")
//public class AuthFilter implements Filter {
//
//    private static final Set<String> publicPath = Set.of(LOGIN_PATH,REGISTRATION_PATH,START_PATH,GO_APP_PATH,"/index.jsp");
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
////        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        HttpServletRequest req = ((HttpServletRequest) servletRequest);
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        String requestURI = req.getRequestURI();
//        if(isPublicPath(requestURI) || isUserLoggedIn(servletRequest)) {
//            filterChain.doFilter(servletRequest,servletResponse);
//        } else {
//            req.getRequestDispatcher(JspHelper.getPath(START_PATH));
//        }
//    }
//
//    private boolean isUserLoggedIn(ServletRequest servletRequest) {
//        //TODO find all users in session
////        LoginUserDto user = (LoginUserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
//        return false;
//    }
//
//    private boolean isPublicPath(String requestURI) {
//        return publicPath.stream().anyMatch(requestURI::startsWith);
//    }
//
//}
