package com.hardziyevich.application.domain.service.mapper.impl;

import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.domain.service.dto.LoginUserDto;
import com.hardziyevich.application.domain.service.mapper.MapperService;

public class LoginMapperService implements MapperService<User, LoginUserDto> {

    private static final LoginMapperService instance = new LoginMapperService();

    @Override
    public LoginUserDto mapFrom(User object) {
        return LoginUserDto.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .email(object.getEmail())
                .type(object.getType().toString())
                .build();
    }

    public static LoginMapperService getInstance() {
        return instance;
    }
}
