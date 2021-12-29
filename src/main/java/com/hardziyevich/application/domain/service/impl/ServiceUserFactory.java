package com.hardziyevich.application.domain.service.impl;

import com.hardziyevich.application.dao.DaoUser;
import com.hardziyevich.application.dao.impl.DaoFactory;
import com.hardziyevich.application.domain.service.ServiceUser;

public class ServiceUserFactory {

    private ServiceUserFactory() {throw new UnsupportedOperationException();}

    public static ServiceUser newServiceUser(){
        return new UserService(DaoFactory.newUserDao());
    }

    public static ServiceUser testServiceUser(DaoUser user) {
        return new UserService(user);
    }
}
