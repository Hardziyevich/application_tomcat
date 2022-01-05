package com.hardziyevich.application.controller.command.impl;

import com.hardziyevich.application.controller.command.Command;
import com.hardziyevich.application.controller.command.Router;
import com.hardziyevich.application.controller.servlet.Util;
import com.hardziyevich.application.domain.service.ServiceUser;
import com.hardziyevich.application.domain.service.ServiceUserFactory;
import com.hardziyevich.application.domain.service.dto.UserDto;
import com.hardziyevich.application.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.hardziyevich.application.controller.servlet.ConstantProperty.UrlPath.*;
import static com.hardziyevich.application.controller.servlet.ConstantProperty.UserAttribute.*;
import static com.hardziyevich.application.controller.servlet.ConstantProperty.UserAttribute.ROLE;

public class RegistrationCommand implements Command {

    private static final String FIELD_NOT_VALID = "validUser";
    private ServiceUser userFactory;

    @Override
    public Router execute(HttpServletRequest req) {
        Router router = Router.REDIRECT;
        UserDto userDto = UserDto.builder()
                .firstName(req.getParameter(FIRST_NAME))
                .lastName(req.getParameter(LAST_NAME))
                .email(req.getParameter(LOGIN))
                .password(req.getParameter(PASSWORD))
                .type(req.getParameter(ROLE))
                .build();
        try {
            userFactory = ServiceUserFactory.newServiceUser();
            List<String> validation = userFactory.create(userDto);
            if(validation.isEmpty()) {
                router = Router.FORWARD;
                router.setPagePath(Util.getJspPath(LOGIN_PATH));
            } else {
                req.getSession().setAttribute(FIELD_NOT_VALID, validation);
                req.getSession().setAttribute(PARAM_USER_DTO,userDto);
                router.setPagePath(GO_REGISTRATION_PATH + REGISTRATION_ERROR_PATH);
            }
        } catch (ServiceException e) {
            router = exceptionHandler(req,e.getMessage());
        }
        return router;
    }
}
