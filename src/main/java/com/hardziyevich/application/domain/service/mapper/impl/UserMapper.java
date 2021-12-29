package com.hardziyevich.application.domain.service.mapper.impl;

import com.hardziyevich.application.domain.entity.Role;
import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.domain.service.dto.UserDto;
import com.hardziyevich.application.domain.service.mapper.Mapper;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class UserMapper implements Mapper<UserDto, User> {

    private static final UserMapper instance = new UserMapper();

    @Override
    public User mapFrom(UserDto object) {
        String encodePassword = new String(new Base64().encode(object.getPassword().getBytes(StandardCharsets.UTF_8)));
        return User.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .login(object.getLogin())
                .password(encodePassword)
                .type(Role.valueOf(object.getType()))
                .build();
    }

    public static UserMapper getInstance() {
        return instance;
    }
}
