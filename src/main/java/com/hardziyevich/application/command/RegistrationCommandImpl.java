package com.hardziyevich.application.command;

import com.hardziyevich.application.entity.Role;
import com.hardziyevich.application.service.UserService;
import com.hardziyevich.application.service.dto.UserDto;
import com.hardziyevich.application.servlet.JspHelper;
import com.hardziyevich.application.validator.Test;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.hardziyevich.application.servlet.ConstantServlet.JspPath.REGISTRATION_JSP_PATH;
import static com.hardziyevich.application.servlet.ConstantServlet.UrlPath.LOGIN_PATH;
import static com.hardziyevich.application.servlet.ConstantServlet.UrlPath.REGISTRATION_ERROR_PATH;
import static com.hardziyevich.application.servlet.ConstantServlet.UserAttribute.*;
import static com.hardziyevich.application.servlet.ConstantServlet.UserAttribute.ROLE;

public class RegistrationCommandImpl implements Command {

    private final UserService userService = UserService.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = UserDto.builder()
                .firstName(req.getParameter(FIRST_NAME))
                .lastName(req.getParameter(LAST_NAME))
                .login(req.getParameter(LOGIN))
                .password(req.getParameter(PASSWORD))
                .type(req.getParameter(ROLE))
                .build();
        Test test = new Test();
        if (test.isValid(userDto)) {
            userService.create(userDto);
            resp.sendRedirect(LOGIN_PATH);
        } else {
            req.getSession().setAttribute("mapValidUser", test.getContents());
            resp.sendRedirect(REGISTRATION_ERROR_PATH);
        }
    }
}
