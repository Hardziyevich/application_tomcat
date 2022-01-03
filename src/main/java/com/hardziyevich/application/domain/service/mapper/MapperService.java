package com.hardziyevich.application.domain.service.mapper;

import java.sql.SQLException;

public interface MapperService<F,T> {

    T mapFrom(F object) throws SQLException;

}
