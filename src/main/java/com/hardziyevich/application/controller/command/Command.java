package com.hardziyevich.application.controller.command;

import com.hardziyevich.application.controller.servlet.Util;
import jakarta.servlet.http.HttpServletRequest;

import static com.hardziyevich.application.controller.servlet.ConstantProperty.UrlPath.ERROR;
import static com.hardziyevich.application.controller.servlet.ConstantProperty.UserAttribute.PARAM_EXCEPTION;

public interface Command {

    Router execute(HttpServletRequest req);

    default Router exceptionHandler(HttpServletRequest req,String message) {
        Router router = Router.FORWARD;
        req.getSession().setAttribute(PARAM_EXCEPTION,message);
        router.setPagePath(Util.getJspPath(ERROR));
        return router;
    }

}
