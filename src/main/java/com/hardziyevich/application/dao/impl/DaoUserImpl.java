package com.hardziyevich.application.dao.impl;

import com.hardziyevich.application.dao.connectionpool.ConnectionPool;
import com.hardziyevich.application.dao.DaoUser;
import com.hardziyevich.application.dao.mapper.impl.UserMapperDao;
import com.hardziyevich.application.exception.DaoException;
import com.hardziyevich.application.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.hardziyevich.application.dao.impl.SqlRequest.DELIMITER;
import static com.hardziyevich.application.dao.impl.SqlRequest.Insert.INSERT_TABLE;
import static com.hardziyevich.application.dao.impl.SqlRequest.Insert.INSERT_VALUE_USER;
import static com.hardziyevich.application.dao.impl.SqlRequest.Select.*;
import static com.hardziyevich.application.dao.impl.SqlRequest.Tables.TABLE_USERS;


public class DaoUserImpl implements DaoUser {

    private static final Logger log = LoggerFactory.getLogger(DaoUserImpl.class);

    private static DaoUser instance;

    private final ConnectionPool connectionPool;
    private final UserMapperDao userMapper = UserMapperDao.getInstance();

    DaoUserImpl(ConnectionPool connection) {
        this.connectionPool = connection;
    }

    @Override
    public boolean create(User user) throws DaoException {
        boolean result = false;
        String table = INSERT_TABLE.formatted(TABLE_USERS);
        String sql = String.join(DELIMITER, table, INSERT_VALUE_USER);
        try (Connection connection = connectionPool.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            DaoUtil.setStatement(preparedStatement, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getType().toString());
            preparedStatement.executeUpdate();
            result = preparedStatement.getUpdateCount() == 1;
        } catch (SQLException e) {
            log.warn("Can not create user {}", e.getMessage());
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<User> findByEmail(String email) throws DaoException {
        List<User> users;
        String sql = String.join(DELIMITER, SELECT_USERS, SELECT_WHERE, SELECT_USERS_EMAIL);
        try (Connection connection = connectionPool.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            DaoUtil.setStatement(preparedStatement, email);
            users = getUsers(preparedStatement);
        } catch (SQLException e) {
            log.warn("Can not find user in specification {}", e.getMessage());
            throw new DaoException(e);
        }
        return users;
    }

    public List<User> findByEmailAndPassword(String email, String password) throws DaoException {
        List<User> users;
        String sql = String.join(DELIMITER, SELECT_USERS, SELECT_WHERE, SELECT_USERS_EMAIL, SELECT_AND, SELECT_USERS_PASSWORD);
        try (Connection connection = connectionPool.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            DaoUtil.setStatement(preparedStatement, email,password);
            users = getUsers(preparedStatement);
        } catch (SQLException e) {
            log.warn("Can not find user in specification {}", e.getMessage());
            throw new DaoException(e);
        }
        return users;
    }

    public static DaoUser getInstance(ConnectionPool connection) {
        if (instance == null) {
            instance = new DaoUserImpl(connection);
        }
        return instance;
    }

    private List<User> getUsers(PreparedStatement preparedStatement) throws SQLException {
        List<User> users = new ArrayList<>();
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                users.add(userMapper.mapFrom(resultSet));
            }
        }
        return users;
    }
}
