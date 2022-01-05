package com.hardziyevich.application.controller.command.impl.error;

import com.hardziyevich.application.controller.command.Command;
import com.hardziyevich.application.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;


public class ErrorCommand implements Command {
    @Override
    public Router execute(HttpServletRequest req){
        String message = "Command is not find";
        return exceptionHandler(req,message);
    }
}
