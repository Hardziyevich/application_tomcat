package com.hardziyevich.application.dao;

import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.exception.DaoException;

public interface DaoUser {
    boolean create(User user) throws DaoException;
}
