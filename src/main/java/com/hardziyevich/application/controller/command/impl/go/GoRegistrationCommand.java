package com.hardziyevich.application.controller.command.impl.go;

import com.hardziyevich.application.controller.command.Command;
import com.hardziyevich.application.controller.command.Router;
import com.hardziyevich.application.domain.entity.Role;
import com.hardziyevich.application.controller.servlet.Util;
import jakarta.servlet.http.HttpServletRequest;

import static com.hardziyevich.application.controller.servlet.ConstantProperty.UrlPath.REGISTRATION_PATH;
import static com.hardziyevich.application.controller.servlet.ConstantProperty.UserAttribute.ROLES;

public class GoRegistrationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req) {
        req.setAttribute(ROLES, Role.values());
        Router router = Router.FORWARD;
        router.setPagePath(Util.getJspPath(REGISTRATION_PATH));
        return router;
    }
}
