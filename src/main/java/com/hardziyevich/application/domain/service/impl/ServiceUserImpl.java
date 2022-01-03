package com.hardziyevich.application.domain.service.impl;

import com.hardziyevich.application.dao.DaoUser;
import com.hardziyevich.application.dao.Specification;
import com.hardziyevich.application.dao.impl.DaoUserImpl;
import com.hardziyevich.application.dao.impl.FindUserByEmailSpecification;
import com.hardziyevich.application.domain.entity.Role;
import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.domain.service.validator.Validator;
import com.hardziyevich.application.exception.DaoException;
import com.hardziyevich.application.exception.ServiceException;
import com.hardziyevich.application.domain.service.ServiceUser;
import com.hardziyevich.application.domain.service.dto.UserDto;
import com.hardziyevich.application.domain.service.mapper.impl.LoginMapperService;
import com.hardziyevich.application.domain.service.mapper.impl.UserMapperService;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hardziyevich.application.domain.service.validator.ConstantValid.*;

public class ServiceUserImpl implements ServiceUser {

    private static final Logger log = LoggerFactory.getLogger(DaoUserImpl.class);

    private static ServiceUser instance;

    private final DaoUser daoUser;
    private final UserMapperService userMapper = UserMapperService.getInstance();
    private final LoginMapperService loginMapper = LoginMapperService.getInstance();

    ServiceUserImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    public List<String> create(UserDto userDto) throws ServiceException {
        UserDto dto = Optional.ofNullable(userDto).orElseThrow(ServiceException::new);
        Validator<UserDto> validator = Validator.of(dto)
                .validator(d -> !d.getFirstName().isEmpty(), NAME_NOT_VALID)
                .validator(d -> !d.getLastName().isEmpty(), LAST_NAME_NOT_VALID)
                .validator(d -> EmailValidator.getInstance().isValid(d.getEmail()), EMAIL_NOT_VALID)
                .validator(d -> !d.getPassword().isEmpty(),PASSWORD_NOT_VALID);
        if(validator.isEmpty()) {
            try {
                List<User> users = daoUser.find(new FindUserByEmailSpecification(userDto.getEmail()));
                if(users.isEmpty()) {
                    User user = userMapper.mapFrom(userDto);
                    daoUser.create(user);
                } else {
                    validator.validator(d -> users.isEmpty(),USER_EXIST);
                }
            } catch (DaoException e) {
                log.warn("The user was not created in the service {}", e.getMessage());
                throw new ServiceException(e.getMessage());
            }
        }
        return validator.getContainer();
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