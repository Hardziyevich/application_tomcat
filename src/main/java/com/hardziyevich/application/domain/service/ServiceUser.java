package com.hardziyevich.application.domain.service;

import com.hardziyevich.application.domain.service.dto.LoginUserDto;
import com.hardziyevich.application.exception.ServiceException;
import com.hardziyevich.application.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface ServiceUser {

    List<String> create(UserDto userDto) throws ServiceException;
    Optional<LoginUserDto> login(UserDto userDto) throws ServiceException;
}
