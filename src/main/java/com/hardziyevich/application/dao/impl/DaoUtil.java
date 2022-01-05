package com.hardziyevich.application.dao.impl;

import com.hardziyevich.application.dao.mapper.impl.UserMapperDao;
import com.hardziyevich.application.domain.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;


class DaoUtil {

    private DaoUtil() {
    }

    static void setStatement(PreparedStatement preparedStatement, Object... seq) throws SQLException {
        for (int i = 1; i < seq.length + 1; i++) {
            preparedStatement.setObject(i, seq[i - 1]);
        }
    }
}
