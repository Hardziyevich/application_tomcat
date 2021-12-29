package com.hardziyevich.application.domain.service.impl;

import com.hardziyevich.application.dao.DaoUser;
import com.hardziyevich.application.dao.impl.UserDao;
import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.execption.DaoException;
import com.hardziyevich.application.execption.ServiceException;
import com.hardziyevich.application.domain.service.ServiceUser;
import com.hardziyevich.application.domain.service.dto.UserDto;
import com.hardziyevich.application.domain.service.mapper.impl.LoginMapper;
import com.hardziyevich.application.domain.service.mapper.impl.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService implements ServiceUser {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    private final DaoUser daoUser;
    private final UserMapper userMapper = UserMapper.getInstance();
    private final LoginMapper loginMapper = LoginMapper.getInstance();

    UserService(DaoUser daoUser) {
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

//    public Optional<LoginUserDto> login(String email, String password) {
//        return userDaoTest.findByEmailAndPassword(email,password)
//                .map(loginMapper::mapFrom);
//    }
}