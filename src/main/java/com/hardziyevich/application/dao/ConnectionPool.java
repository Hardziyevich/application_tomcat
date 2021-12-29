package com.hardziyevich.application.dao;

import com.hardziyevich.application.execption.DaoException;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public enum ConnectionPool {

    INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);

    private static final String PASSWORD_KEY = "db.password";
    private static final String USERNAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";
    private static final String POOL_SIZE = "db.pool";
    private static final String SQL_DRIVER = "db.driver";
    private static final Integer DEFAULT_POOL_SIZE = 5;


    private BlockingQueue<Connection> pool;
    private List<Connection> sourceConnection;

    ConnectionPool(){
        try {
            loadDriver();
            initConnectionPool();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    private void initConnectionPool() throws DaoException {
        String poolSizeValue = PropertiesUtil.get(POOL_SIZE);
        int size = poolSizeValue == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSizeValue);
        pool = new ArrayBlockingQueue<>(size);
        sourceConnection = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            creatProxyConnection();
        }
    }

    /**
     * On the one hand method will return connection from connection poll if connection is existing and valid.
     * On the other hand will create new connection and return that.
     * @return Connection to database.
     */
    public Connection openConnection() throws DaoException {
        Connection connection = null;
        try {
            connection = pool.poll(1, TimeUnit.SECONDS);
            if(!(connection != null && connection.isValid(1))){
                creatProxyConnection();
                connection = openConnection();
            }
        } catch (InterruptedException | SQLException e) {
            log.warn("Can not open connection {}", e.getMessage());
            Thread.currentThread().interrupt();
            throw new DaoException(e.getMessage());
        }
        return connection;
    }


    /**
     * Methode make proxy connection, when connection is closed, proxy return him in BlockingQueue.
     */
    private void creatProxyConnection() throws DaoException {
        final Connection connection;
        try {
            connection = postgreSqlDataSource().getConnection();
            Object proxyConnection = Proxy.newProxyInstance(ConnectionPool.class.getClassLoader(), new Class[]{Connection.class},
                    (proxy, method, args) -> method.getName().equals("close") ? pool.add((Connection) proxy) : method.invoke(connection, args));

            pool.add((Connection) proxyConnection);
            sourceConnection.add(connection);
        } catch (SQLException e) {
            log.warn("Can not create connection pool {}", e.getMessage());
            throw new DaoException(e.getMessage());
        }
    }

    public void destroyPool() throws DaoException {
        for (Connection connection : sourceConnection) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.warn("Can not destroy poll {}", e.getMessage());
                throw new DaoException(e.getMessage());
            }
        }
    }

    private DataSource postgreSqlDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(PropertiesUtil.get(URL_KEY));
        dataSource.setUser(PropertiesUtil.get(USERNAME_KEY));
        dataSource.setPassword(PropertiesUtil.get(PASSWORD_KEY));
        return dataSource;
    }

    private void loadDriver() throws DaoException {
        try {
            Class.forName(PropertiesUtil.get(SQL_DRIVER));
        } catch (ClassNotFoundException e) {
            log.warn("Can not load driver {}", e.getMessage());
            throw new DaoException(e.getMessage());
        }
    }
}
