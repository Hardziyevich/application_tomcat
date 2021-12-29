//package com.hardziyevich.application.command.impl;
//
//import com.hardziyevich.application.command.Command;
//import com.hardziyevich.application.service.UserService;
//import com.hardziyevich.application.service.dto.LoginUserDto;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//import static com.hardziyevich.application.servlet.ConstantServlet.UrlPath.LOGIN_ERROR_PATH;
//import static com.hardziyevich.application.servlet.ConstantServlet.UrlPath.RESISTORS_PATH;
//import static com.hardziyevich.application.servlet.ConstantServlet.UserAttribute.LOGIN;
//import static com.hardziyevich.application.servlet.ConstantServlet.UserAttribute.PASSWORD;
//
//public class LoginCommand implements Command {
//
//    private final UserService userService = UserService.getInstance();
//
//    @Override
//    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        userService.login(req.getParameter(LOGIN), req.getParameter(PASSWORD))
//                .ifPresentOrElse(
//                        user -> onLogin(user, req, resp),
//                        () -> onLoginFail(req, resp)
//                );
//    }
//
//    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            resp.sendRedirect(LOGIN_ERROR_PATH + req.getParameter(LOGIN));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void onLogin(LoginUserDto userDto, HttpServletRequest req, HttpServletResponse resp) {
//        //TODO set all users
//        req.getSession().setAttribute("user", userDto);
//        try {
//            //TODO redirect to user or admin path
//            resp.sendRedirect(RESISTORS_PATH);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
