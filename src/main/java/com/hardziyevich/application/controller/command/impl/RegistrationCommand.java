package com.hardziyevich.application.controller.command.impl;

import com.hardziyevich.application.controller.command.Command;
import com.hardziyevich.application.controller.command.Router;
import com.hardziyevich.application.domain.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

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
                .email(req.getParameter(LOGIN))
                .password(req.getParameter(PASSWORD))
                .type(req.getParameter(ROLE))
                .build();
//        if (userValidation.isValid(userDto)) {
//            router = Router.REDIRECT;
//            boolean isCreate = false;
//            try {
//                ServiceUser userService = ServiceUserFactory.newServiceUser();
//                isCreate = userService.create(userDto);
//            } catch (ServiceException e) {
//                //TODO add error page
//                router.setPagePath(JspHelper.getPath(""));
//            }
//            if(isCreate) {
//                router.setPagePath(JspHelper.getPath(LOGIN_PATH));
//            } else {
//                //TODO add error page
//                router.setPagePath(JspHelper.getPath(""));
//            }
//        } else {
//            req.getSession().setAttribute(FIELD_NOT_VALID, userValidation.getContents());
//            router = Router.REDIRECT;
//            router.setPagePath(GO_REGISTRATION_PATH + REGISTRATION_ERROR_PATH);
//        }
//        return router;
        return null;
    }
}
