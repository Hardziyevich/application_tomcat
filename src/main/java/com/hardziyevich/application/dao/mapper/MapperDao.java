package com.hardziyevich.application.dao.mapper;

import java.sql.SQLException;

public interface MapperDao<F,T> {

    T mapFrom(F object) throws SQLException;

}
