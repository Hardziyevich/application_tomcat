package com.hardziyevich.application.dao;

import com.hardziyevich.application.domain.entity.User;
import com.hardziyevich.application.exception.DaoException;

import java.util.List;

public interface DaoUser {
    boolean create(User user) throws DaoException;
    List<User> find(Specification specification) throws DaoException;
}
