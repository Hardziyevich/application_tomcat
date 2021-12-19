package com.hardziyevich.application.service.mapper;

import com.hardziyevich.application.entity.Role;
import com.hardziyevich.application.entity.User;
import com.hardziyevich.application.service.dto.UserDto;

public class UserMapper implements Mapper<UserDto, User>{

    private static final UserMapper instance = new UserMapper();

    @Override
    public User mapFrom(UserDto object) {
        return User.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .password(object.getPassword())
                .login(object.getLogin())
                .type(Role.valueOf(object.getType()))
                .build();
    }

    public static UserMapper getInstance() {
        return instance;
    }
}
