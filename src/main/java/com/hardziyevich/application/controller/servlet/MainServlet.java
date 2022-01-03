package com.hardziyevich.application.controller.servlet;

import com.hardziyevich.application.controller.command.Command;
import com.hardziyevich.application.controller.command.CommandProvider;
import com.hardziyevich.application.controller.command.Router;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;


@WebServlet(urlPatterns = {"/registration","/command"})
public class MainServlet extends HttpServlet {

    private CommandProvider provide = CommandProvider.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getRequestURI() + "   request param ");
        System.out.println(req.getHeader("Accept-Language"));
        req.getParameterMap().entrySet().forEach(e -> System.out.println(e.getKey() + " " + Arrays.toString(e.getValue())));
        System.out.println(req.getMethod());
        String param = req.getParameter("command");
        Command command = provide.getCommand(param);
        Router router = command.execute(req);
        switch (router) {
            case FORWARD -> req.getRequestDispatcher(router.getPagePath()).forward(req,resp);
            case REDIRECT -> resp.sendRedirect(router.getPagePath());
        }
    }
}
