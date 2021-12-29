package com.hardziyevich.application.dao;

import com.hardziyevich.application.dao.connectionpool.ConnectionPoolFabric;
import com.hardziyevich.application.dao.impl.DaoUserImpl;
import com.hardziyevich.application.exception.DaoException;

import java.util.Map;

public class DaoFactory {

    private DaoFactory() {
    }

    public static DaoUser newUserDao() throws DaoException {
        return DaoUserImpl.getInstance(ConnectionPoolFabric.defaultConnection());
    }

    public static DaoUser flexibleUserDao(Map<String,String> propertyConnection) throws DaoException {
        return DaoUserImpl.getInstance(ConnectionPoolFabric.flexibleConnection(propertyConnection));
    }

}
