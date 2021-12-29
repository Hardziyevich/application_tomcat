package com.hardziyevich.application.domain.service;

import com.hardziyevich.application.execption.ServiceException;
import com.hardziyevich.application.domain.service.dto.UserDto;

public interface ServiceUser {

    boolean create(UserDto userDto) throws ServiceException;
}
