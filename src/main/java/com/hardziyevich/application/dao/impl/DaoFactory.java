package com.hardziyevich.application.dao.impl;

import com.hardziyevich.application.dao.ConnectionPool;

public class DaoFactory {

    private DaoFactory() {
        throw new UnsupportedOperationException();
    }

    public static UserDao newUserDao() {
        return new UserDao(ConnectionPool.INSTANCE);
    }

    public static UserDao testUserDao(ConnectionPool connectionPool) {
        return new UserDao(ConnectionPool.INSTANCE);
    }
}
