package com.hardziyevich.application.controller.command;

public enum CommandType {
    GO_APP,
    GO_REGISTRATION,
    LOCALE,
    REGISTRATION,
    LOGIN,
    START_PAGE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
