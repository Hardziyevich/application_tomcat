package com.hardziyevich.application.exception;

public class ServiceException extends Exception{

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable throwable) {super(throwable);}

}
