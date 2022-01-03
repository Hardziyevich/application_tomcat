package com.hardziyevich.application.controller.servlet;

public final class ConstantProperty {

    private ConstantProperty() { throw new UnsupportedOperationException();}

    public final static class UrlPath {

        private UrlPath() { throw new UnsupportedOperationException();}

        public static final String GO_APP_PATH = "/command";
        public static final String GO_REGISTRATION_PATH = "/command?command=go_registration";
        public static final String REGISTRATION_PATH = "/registration";
        public static final String START_PATH = "/start";
        public static final String LOGIN_PATH = "/login";
        public static final String LOGIN_ERROR_PATH = LOGIN_PATH + "?error&command=not_valid_registration";
        public static final String LOGOUT_PATH = "/logout";
        public static final String REGISTRATION_ERROR_PATH = "&error=registration";
        public static final String RESISTORS_PATH = "/resistors";

    }

    public final static class UserAttribute {

        private UserAttribute() { throw new UnsupportedOperationException();}

        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String ROLES = "roles";
        public static final String ROLE = "role";

    }
}
