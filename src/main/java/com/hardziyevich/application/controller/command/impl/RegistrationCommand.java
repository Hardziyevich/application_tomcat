package com.hardziyevich.application.controller.command.impl;

import com.hardziyevich.application.controller.command.Command;
import com.hardziyevich.application.controller.command.Router;
import com.hardziyevich.application.domain.entity.Role;
import com.hardziyevich.application.exception.ServiceException;
import com.hardziyevich.application.domain.service.ServiceUser;
import com.hardziyevich.application.domain.service.ServiceUserFactory;
import com.hardziyevich.application.domain.service.dto.UserDto;
import com.hardziyevich.application.controller.servlet.JspHelper;
import com.hardziyevich.application.controller.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;

import static com.hardziyevich.application.controller.servlet.ConstantProperty.UrlPath.*;
import static com.hardziyevich.application.controller.servlet.ConstantProperty.UserAttribute.*;
import static com.hardziyevich.application.controller.servlet.ConstantProperty.UserAttribute.ROLE;

public class RegistrationCommand implements Command {

    private static final String FIELD_NOT_VALID = "mapValidUser";


    @Override
    public Router execute(HttpServletRequest req) {
        Router router;
        UserDto userDto = UserDto.builder()
                .firstName(req.getParameter(FIRST_NAME))
                .lastName(req.getParameter(LAST_NAME))
                .login(req.getParameter(LOGIN))
                .password(req.getParameter(PASSWORD))
                .type(req.getParameter(ROLE))
                .build();
        UserValidator userValidation = new UserValidator();
        if (userValidation.isValid(userDto)) {
            router = Router.REDIRECT;
            boolean isCreate = false;
            try {
                ServiceUser userService = ServiceUserFactory.newServiceUser();
                isCreate = userService.create(userDto);
            } catch (ServiceException e) {
                //TODO add error page
                router.setPagePath(JspHelper.getPath(""));
            }
            if(isCreate) {
                router.setPagePath(JspHelper.getPath(LOGIN_PATH));
            } else {
                //TODO add error page
                router.setPagePath(JspHelper.getPath(""));
            }
        } else {
            req.setAttribute(ROLES, Role.values());
            req.getSession().setAttribute(FIELD_NOT_VALID, userValidation.getContents());
            router = Router.FORWARD;
            router.setPagePath(JspHelper.getPath(REGISTRATION_PATH) + REGISTRATION_ERROR_PATH);
        }
        return router;
    }
}
