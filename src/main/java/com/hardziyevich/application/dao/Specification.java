package com.hardziyevich.application.dao;

import com.hardziyevich.application.dao.connectionpool.ConnectionPool;
import com.hardziyevich.application.exception.DaoException;

import java.sql.PreparedStatement;

public interface Specification {

    PreparedStatement searchFilter(ConnectionPool connectionPool) throws DaoException;
}
