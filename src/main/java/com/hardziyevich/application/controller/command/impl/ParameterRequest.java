package com.hardziyevich.application.controller.command.impl;

public enum ParameterRequest {
    COMMAND,
    ERROR;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
