package com.hardziyevich.application.domain.service.mapper.impl;

import com.hardziyevich.application.controller.servlet.Util;
import com.hardziyevich.application.domain.entity.Role;
import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.domain.service.dto.UserDto;
import com.hardziyevich.application.domain.service.mapper.MapperService;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class UserMapperService implements MapperService<UserDto, User> {

    private static final UserMapperService instance = new UserMapperService();

    private UserMapperService() {}

    @Override
    public User mapFrom(UserDto object) {
        return User.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .email(object.getEmail())
                .password(Util.encode(object.getPassword()))
                .type(Role.valueOf(object.getType()))
                .build();
    }

    public static UserMapperService getInstance() {
        return instance;
    }
}
