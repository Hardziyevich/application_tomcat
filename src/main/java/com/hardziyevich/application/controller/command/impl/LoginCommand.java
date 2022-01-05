package com.hardziyevich.application.controller.command.impl;

import com.hardziyevich.application.controller.command.Command;
import com.hardziyevich.application.controller.command.Router;
import com.hardziyevich.application.controller.servlet.Util;
import com.hardziyevich.application.domain.service.ServiceUser;
import com.hardziyevich.application.domain.service.ServiceUserFactory;
import com.hardziyevich.application.domain.service.dto.UserDto;
import com.hardziyevich.application.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

import static com.hardziyevich.application.controller.servlet.ConstantProperty.UrlPath.*;
import static com.hardziyevich.application.controller.servlet.ConstantProperty.UserAttribute.*;


public class LoginCommand implements Command {

    private ServiceUser userFactory;

    @Override
    public Router execute(HttpServletRequest req) {
        Router result;
        UserDto userDto = UserDto.builder()
                .email(req.getParameter(LOGIN))
                .password(req.getParameter(PASSWORD))
                .build();
        try {
            Router router = Router.REDIRECT;
            userFactory = ServiceUserFactory.newServiceUser();
            userFactory.login(userDto).ifPresentOrElse(
                    user -> {
                        req.getSession().setAttribute(user.getType(), user);
                        router.setPagePath(RESISTORS_PATH);
                    },
                    () -> {
                        req.getSession().setAttribute(PARAM_EMAIL,userDto.getEmail());
                        router.setPagePath(GO_LOGIN_PATH + LOGIN_ERROR_PATH);
                    }
            );
            result = router;
        } catch (ServiceException e) {
            result = exceptionHandler(req,e.getMessage());
        }
        return result;
    }

//
//    private void onLogin(LoginUserDto userDto, HttpServletRequest req, HttpServletResponse resp) {
//        //TODO set all users
//        req.getSession().setAttribute("user", userDto);
//        try {
//            //TODO redirect to user or admin path
//            resp.sendRedirect(RESISTORS_PATH);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
