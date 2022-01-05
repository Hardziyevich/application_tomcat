package com.hardziyevich.application.controller.command;

public enum CommandType {
    GO_APP,
    GO_REGISTRATION,
    GO_LOGIN,
    GO_START,
    LOCALE,
    REGISTRATION,
    LOGIN;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
