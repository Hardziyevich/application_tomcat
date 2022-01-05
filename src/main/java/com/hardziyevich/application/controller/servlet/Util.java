package com.hardziyevich.application.controller.servlet;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class Util {

    private static final String JSP_FORMAT = "/jsp%s.jsp";

    public static String getJspPath(String jspName) {
        return String.format(JSP_FORMAT, jspName);
    }

    public static String encode(String password) {
        return new String(Base64.encodeBase64(password.getBytes(StandardCharsets.UTF_8)));
    }

    public static String decode(String password) {
        return new String(Base64.decodeBase64(password.getBytes(StandardCharsets.UTF_8)));
    }
}
