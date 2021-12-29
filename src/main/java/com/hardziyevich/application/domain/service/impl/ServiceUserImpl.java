package com.hardziyevich.application.domain.service.impl;

import com.hardziyevich.application.dao.DaoUser;
import com.hardziyevich.application.dao.impl.DaoUserImpl;
import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.exception.DaoException;
import com.hardziyevich.application.exception.ServiceException;
import com.hardziyevich.application.domain.service.ServiceUser;
import com.hardziyevich.application.domain.service.dto.UserDto;
import com.hardziyevich.application.domain.service.mapper.impl.LoginMapper;
import com.hardziyevich.application.domain.service.mapper.impl.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceUserImpl implements ServiceUser {

    private static final Logger log = LoggerFactory.getLogger(DaoUserImpl.class);

    private static ServiceUser instance;

    private final DaoUser daoUser;
    private final UserMapper userMapper = UserMapper.getInstance();
    private final LoginMapper loginMapper = LoginMapper.getInstance();

    ServiceUserImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    public boolean create(UserDto userDto) throws ServiceException {
        boolean result = false;
        User user = userMapper.mapFrom(userDto);
        try {
            result = daoUser.create(user);
        } catch (DaoException e) {
            log.warn("The user was not created in the service {}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return result;
    }

    public static ServiceUser getInstance(DaoUser daoUser) {
        if(instance == null) {
            instance = new ServiceUserImpl(daoUser);
        }
        return instance;
    }

    //    public Optional<LoginUserDto> login(String email, String password) {
//        return userDaoTest.findByEmailAndPassword(email,password)
//                .map(loginMapper::mapFrom);
//    }
}