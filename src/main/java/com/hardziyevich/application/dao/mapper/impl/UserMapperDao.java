package com.hardziyevich.application.dao.mapper.impl;

import com.hardziyevich.application.dao.mapper.MapperDao;
import com.hardziyevich.application.domain.entity.Role;
import com.hardziyevich.application.domain.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapperDao implements MapperDao<ResultSet, User> {

    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String TYPE = "type";

    private static final UserMapperDao instance = new UserMapperDao();

    private UserMapperDao() {}

    @Override
    public User mapFrom(ResultSet object) throws SQLException {
        return User.builder()
                .id(object.getBigDecimal(ID))
                .firstName(object.getString(FIRST_NAME))
                .lastName(object.getString(LAST_NAME))
                .email(object.getString(EMAIL))
                .password(object.getString(PASSWORD))
                .type(Role.findRole(object.getString(TYPE)))
                .build();
    }

    public static UserMapperDao getInstance() {
        return instance;
    }
}
