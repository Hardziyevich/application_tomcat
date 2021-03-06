package com.hardziyevich.application.controller.command.impl.go;

import com.hardziyevich.application.controller.command.Command;
import com.hardziyevich.application.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import static com.hardziyevich.application.controller.servlet.ConstantProperty.UrlPath.START_PATH;

public class GoStartCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req){
        Router result = Router.FORWARD;
        result.setPagePath(START_PATH);
        return result;
    }
}
