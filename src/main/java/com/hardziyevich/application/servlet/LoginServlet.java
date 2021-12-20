package com.hardziyevich.application.servlet;

import com.hardziyevich.application.command.Command;
import com.hardziyevich.application.command.CommandType;
import com.hardziyevich.application.service.UserService;
import com.hardziyevich.application.service.dto.LoginUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static com.hardziyevich.application.servlet.ConstantServlet.UrlPath.*;



@WebServlet(urlPatterns = {"/login","/registration"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(req.getServletPath())).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String param = req.getParameter("command");
            Optional<CommandType> command = Arrays.stream(CommandType.values()).filter(x -> x.toString().equals(param)).findFirst();
            Command commandType = command.get().getCommand();
            commandType.execute(req, resp);
    }
}
