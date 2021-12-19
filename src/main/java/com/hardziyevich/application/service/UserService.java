package com.hardziyevich.application.service;

import com.hardziyevich.application.dao.UserDao;
import com.hardziyevich.application.entity.User;
import com.hardziyevich.application.service.dto.LoginUserDto;
import com.hardziyevich.application.service.dto.UserDto;
import com.hardziyevich.application.service.mapper.LoginMapper;
import com.hardziyevich.application.service.mapper.UserMapper;

import java.util.Optional;

public class UserService {

    private static final UserService instance = new UserService();

    private final UserDao userDao = UserDao.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final LoginMapper loginMapper = LoginMapper.getInstance();

    public Integer create(UserDto userDto) {
        //TODO create object with result valid in JSP
        User user = userMapper.mapFrom(userDto);
        userDao.save(user);
        return user.getId();
    }

    public Optional<LoginUserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email,password)
                .map(loginMapper::mapFrom);
    }

    public static UserService getInstance() {
        return instance;
    }
}
