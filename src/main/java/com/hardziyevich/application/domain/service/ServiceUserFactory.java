package com.hardziyevich.application.domain.service;

import com.hardziyevich.application.dao.DaoUser;
import com.hardziyevich.application.dao.DaoFactory;
import com.hardziyevich.application.domain.service.impl.ServiceUserImpl;
import com.hardziyevich.application.exception.DaoException;
import com.hardziyevich.application.exception.ServiceException;

public class ServiceUserFactory {

    private ServiceUserFactory() {
    }

    public static ServiceUser newServiceUser() throws ServiceException {
        try {
            return ServiceUserImpl.getInstance(DaoFactory.newUserDao());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public static ServiceUser testServiceUser(DaoUser user) {
        return ServiceUserImpl.getInstance(user);
    }

}
