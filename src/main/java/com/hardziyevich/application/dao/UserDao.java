package com.hardziyevich.application.dao;

import com.hardziyevich.application.db.ConnectionPool;
import com.hardziyevich.application.entity.Role;
import com.hardziyevich.application.entity.User;

import java.sql.*;
import java.util.Arrays;
import java.util.Optional;

import static java.sql.Statement.*;

public class UserDao {

    private static final UserDao instance = new UserDao();

    private static final String SAVE_SQL = "INSERT INTO component.users (first_name,last_name,login,password,user_type) VALUES (?,?,?,?,?::component.user_type);";
    private static final String FIND_BY_EMAIL_PASSWORD = "SELECT * FROM component.users WHERE login=? AND password=?";

    public User save(User user) {
        try (Connection connection = ConnectionPool.INSTANCE.openConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            statement.setObject(1, user.getFirstName());
            statement.setObject(2, user.getLastName());
            statement.setObject(3, user.getLogin());
            statement.setObject(4, user.getPassword());
            statement.setObject(5, user.getType().toString());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getObject("id", Integer.class));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        User user = null;
        try (Connection connection = ConnectionPool.INSTANCE.openConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL_PASSWORD)) {
            statement.setString(1,email);
            statement.setString(2,password);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){

                user = getUser(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    private User getUser(ResultSet rs) throws SQLException {
        String type = rs.getString("user_type");
        return User.builder()
                .firstName(rs.getString("first_name"))
                .firstName(rs.getString("last_name"))
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .type(Role.findRole(type).get())
                .build();
    }

    public static UserDao getInstance() {
        return instance;
    }
}
