package com.hardziyevich.application.execption;

import com.hardziyevich.application.dao.impl.DaoFactory;

public class DaoException extends Exception{

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable throwable) {super(throwable);}

}
