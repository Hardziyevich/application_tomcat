package com.hardziyevich.application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.hardziyevich.application.servlet.ConstantServlet.UrlPath.LOGIN_PATH;
import static com.hardziyevich.application.servlet.ConstantServlet.UrlPath.LOGOUT_PATH;


@WebServlet(LOGOUT_PATH)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(LOGIN_PATH);
    }
}
