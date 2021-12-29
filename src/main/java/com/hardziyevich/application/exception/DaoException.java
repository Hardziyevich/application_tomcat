package com.hardziyevich.application.exception;

public class DaoException extends Exception{

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable throwable) {super(throwable);}

}
