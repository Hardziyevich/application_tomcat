package com.hardziyevich.application.command;

public enum CommandType {
    LOGIN(new LoginCommandImpl()),
    REGISTRATION(new RegistrationCommandImpl());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
