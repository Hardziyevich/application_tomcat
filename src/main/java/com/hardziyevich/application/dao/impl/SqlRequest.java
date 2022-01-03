package com.hardziyevich.application.dao.impl;

final class SqlRequest {

    private SqlRequest() {
    }

    final static class Insert {

        public Insert() {
        }

        static final String INSERT_TABLE = "INSERT INTO %s";
        static final String INSERT_VALUE_USER = """
                (first_name, last_name, email, password, type)
                VALUES(?,?,?,?,?::component.user_type)""";

    }

    final static class Select {
        static final String SELECT_USERS = "SELECT id, first_name, last_name, email, password, type FROM component.users ";
        static final String SELECT_USERS_EMAIL = "WHERE email =?";
    }

    final static class Tables {

        private Tables() {
        }

        static final String TABLE_USERS = "component.users";
        static final String TABLE_CASES = "component.cases";
        static final String TABLE_TEMPERATURES = "component.operating_temperature";

    }
}
