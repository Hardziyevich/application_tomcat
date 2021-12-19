package com.hardziyevich.application.servlet;

import com.hardziyevich.application.entity.Role;
import com.hardziyevich.application.service.UserService;
import com.hardziyevich.application.service.dto.UserDto;
import com.hardziyevich.application.validator.Test;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

import static com.hardziyevich.application.servlet.ConstantServlet.JspPath.REGISTRATION_JSP_PATH;
import static com.hardziyevich.application.servlet.ConstantServlet.UrlPath.*;
import static com.hardziyevich.application.servlet.ConstantServlet.UserAttribute.*;


@WebServlet(REGISTRATION_PATH)
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ROLES, Role.values());
        req.getRequestDispatcher(JspHelper.getPath(REGISTRATION_JSP_PATH)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = UserDto.builder()
                .firstName(req.getParameter(FIRST_NAME))
                .lastName(req.getParameter(LAST_NAME))
                .login(req.getParameter(LOGIN))
                .password(req.getParameter(PASSWORD))
                .type(req.getParameter(ROLE))
                .build();
        Test test = new Test();
        if(test.isValid(userDto)) {
            userService.create(userDto);
            resp.sendRedirect(LOGIN_PATH);
        } else {
            req.getSession().setAttribute("mapValidUser",test.getContents());
            resp.sendRedirect(REGISTRATION_ERROR_PATH);
        }
    }
}
