package com.hardziyevich.application.controller.command.impl.go;

import com.hardziyevich.application.controller.command.Command;
import com.hardziyevich.application.controller.command.Router;
import com.hardziyevich.application.controller.servlet.Util;
import jakarta.servlet.http.HttpServletRequest;

import static com.hardziyevich.application.controller.servlet.ConstantProperty.UrlPath.START_PATH;


public class GoAppCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req){
        Router result = Router.FORWARD;
        result.setPagePath(Util.getJspPath(START_PATH));
        return result;
    }
}
