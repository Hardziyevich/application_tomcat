package com.hardziyevich.application.domain.service;

import com.hardziyevich.application.dao.Specification;
import com.hardziyevich.application.exception.ServiceException;
import com.hardziyevich.application.domain.service.dto.UserDto;

import java.util.List;

public interface ServiceUser {

    List<String> create(UserDto userDto) throws ServiceException;
}
