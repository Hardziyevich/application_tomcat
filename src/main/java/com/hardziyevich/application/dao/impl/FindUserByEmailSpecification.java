package com.hardziyevich.application.dao.impl;

import com.hardziyevich.application.dao.Specification;
import com.hardziyevich.application.dao.connectionpool.ConnectionPool;
import com.hardziyevich.application.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.hardziyevich.application.dao.impl.SqlRequest.Select.SELECT_USERS;
import static com.hardziyevich.application.dao.impl.SqlRequest.Select.SELECT_USERS_EMAIL;


public class FindUserByEmailSpecification implements Specification {

    private static final Logger log = LoggerFactory.getLogger(FindUserByEmailSpecification.class);
    private final String email;

    public FindUserByEmailSpecification(String email) {
        this.email = email;
    }

    @Override
    public PreparedStatement searchFilter(ConnectionPool connectionPool) throws DaoException {
        StringBuilder sql = new StringBuilder(SELECT_USERS).append(SELECT_USERS_EMAIL);
        PreparedStatement preparedStatement;
        try (Connection connection = connectionPool.openConnection()) {
            preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setObject(1, email);
        } catch (SQLException e) {
            log.warn("Can not find user in specification {}", e.getMessage());
            throw new DaoException(e);
        }
        return preparedStatement;
    }
}
