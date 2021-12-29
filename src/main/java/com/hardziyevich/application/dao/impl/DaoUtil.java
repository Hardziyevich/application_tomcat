package com.hardziyevich.application.dao.impl;

import com.hardziyevich.application.domain.entity.Role;

import java.sql.PreparedStatement;
import java.sql.SQLException;


class DaoUtil {

    private DaoUtil() {
    }

    static void setStatement(PreparedStatement preparedStatement, Object[] seq) throws SQLException {
        for (int i = 1; i < seq.length + 1; i++) {
            preparedStatement.setObject(i, seq[i - 1]);
        }
    }
}
