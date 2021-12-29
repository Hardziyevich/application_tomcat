package com.hardziyevich.application.dao.impl;

final class SqlRequest {

    private SqlRequest() {
        throw new UnsupportedOperationException();
    }

    final static class Insert {

        public Insert() {
            throw new UnsupportedOperationException();
        }

        static final String INSERT_TABLE = "INSERT INTO %s";
        static final String INSERT_VALUE_USER = """
                (first_name, last_name, email, password, type)
                VALUES(?,?,?,?,?::component.user_type)""";

    }

    final static class Tables {

        private Tables() {
            throw new UnsupportedOperationException();
        }

        static final String TABLE_USERS = "component.users";
        static final String TABLE_CASES = "component.cases";
        static final String TABLE_TEMPERATURES = "component.operating_temperature";

    }
}
