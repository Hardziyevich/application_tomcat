package com.hardziyevich.application.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Command {

    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    default void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
