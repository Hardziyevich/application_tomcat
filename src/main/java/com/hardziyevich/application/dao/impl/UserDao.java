package com.hardziyevich.application.dao.impl;

import com.hardziyevich.application.dao.ConnectionPool;
import com.hardziyevich.application.execption.DaoException;
import com.hardziyevich.application.dao.DaoUser;
import com.hardziyevich.application.domain.entity.Role;
import com.hardziyevich.application.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static com.hardziyevich.application.dao.impl.SqlRequest.Insert.*;

public class UserDao implements DaoUser {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    private final ConnectionPool connectionPool;

    UserDao(ConnectionPool connection) {
        this.connectionPool = connection;
    }

    @Override
    public boolean create(User user) throws DaoException {
        boolean result = false;
        String table = INSERT_TABLE.formatted(SqlRequest.Tables.TABLE_USERS);
        StringBuilder builderSql = new StringBuilder();
        builderSql.append(table).append(INSERT_VALUE_USER);
        Object[] values = {
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getType().toString()
        };
        try (Connection connection = connectionPool.openConnection();
             PreparedStatement statement = connection.prepareStatement(builderSql.toString())) {
            DaoUtil.setStatement(statement,values);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
               result = generatedKeys.getObject("id", Integer.class) != null;
            }
        } catch (SQLException e) {
            log.warn("Can not create user {}", e.getMessage());
            throw new DaoException(e.getMessage());
        }
        return result;
    }

//    public Optional<User> findByEmailAndPassword(String email, String password) {
//        User user = null;
//        try (Connection connection = ConnectionPool.INSTANCE.openConnection();
//             PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL_PASSWORD)) {
//            statement.setString(1,email);
//            statement.setString(2,password);
//            ResultSet rs = statement.executeQuery();
//            if(rs.next()){
//
//                user = getUser(rs);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return Optional.ofNullable(user);
//    }

//    private User getUser(ResultSet rs) throws SQLException {
//        String type = rs.getString("user_type");
//        return User.builder()
//                .firstName(rs.getString("first_name"))
//                .firstName(rs.getString("last_name"))
//                .login(rs.getString("login"))
//                .password(rs.getString("password"))
//                .type(Role.findRole(type))
//                .build();
//    }
}
