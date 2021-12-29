package com.hardziyevich.application.domain.service.mapper.impl;

import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.domain.service.dto.LoginUserDto;
import com.hardziyevich.application.domain.service.mapper.Mapper;

public class LoginMapper implements Mapper<User, LoginUserDto> {

    private static final LoginMapper instance = new LoginMapper();

    @Override
    public LoginUserDto mapFrom(User object) {
        return LoginUserDto.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .login(object.getEmail())
                .type(object.getType().toString())
                .build();
    }

    public static LoginMapper getInstance() {
        return instance;
    }
}
