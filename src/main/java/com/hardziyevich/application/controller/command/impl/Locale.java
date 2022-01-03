package com.hardziyevich.application.controller.command.impl;

import com.hardziyevich.application.controller.command.Command;
import com.hardziyevich.application.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class Locale implements Command {

    private static final String ATTRIBUTE = "lang";

    @Override
    public Router execute(HttpServletRequest req) {
        Router router = Router.REDIRECT;
        String parameter = req.getParameter(ATTRIBUTE);
        req.getSession().setAttribute(ATTRIBUTE,parameter);
        String referer = req.getHeader("referer");
        router.setPagePath(referer);
        return router;
    }
}
