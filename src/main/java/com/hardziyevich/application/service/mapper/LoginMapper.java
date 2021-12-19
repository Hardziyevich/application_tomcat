package com.hardziyevich.application.service.mapper;

import com.hardziyevich.application.entity.User;
import com.hardziyevich.application.service.dto.LoginUserDto;

public class LoginMapper implements Mapper<User, LoginUserDto>{

    private static final LoginMapper instance = new LoginMapper();

    @Override
    public LoginUserDto mapFrom(User object) {
        return LoginUserDto.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .login(object.getLogin())
                .type(object.getType().toString())
                .build();
    }

    public static LoginMapper getInstance() {
        return instance;
    }
}
