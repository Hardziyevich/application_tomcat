package com.hardziyevich.application.servlet;

public class ConstantServlet {

    public static class UrlPath {

        public static final String LOGIN_PATH = "/login";
        public static final String LOGIN_ERROR_PATH = LOGIN_PATH + "?error&email=";
        public static final String LOGOUT_PATH = "/logout";
        public static final String REGISTRATION_PATH = "/registration";
        public static final String REGISTRATION_ERROR_PATH = REGISTRATION_PATH + "?error";
        public static final String RESISTORS_PATH = "/resistors";

    }

    public static class JspPath {

        public static final String LOGIN_JSP_PATH = "login";
        public static final String REGISTRATION_JSP_PATH = "registration";
        public static final String RESISTORS_JSP_PATH = "resistors";
    }

    public static class UserAttribute {

        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String ROLES = "roles";
        public static final String ROLE = "role";

    }
}
