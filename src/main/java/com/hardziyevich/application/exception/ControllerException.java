package com.hardziyevich.application.exception;

public class ControllerException extends Exception{

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Throwable throwable) {super(throwable);}

}
