package com.hardziyevich.application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.hardziyevich.application.servlet.ConstantServlet.JspPath.RESISTORS_JSP_PATH;
import static com.hardziyevich.application.servlet.ConstantServlet.UrlPath.RESISTORS_PATH;

@WebServlet(RESISTORS_PATH)
public class ResistorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(RESISTORS_JSP_PATH)).forward(req,resp);
    }
}
