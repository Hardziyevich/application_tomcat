package com.hardziyevich.application.dao.connectionpool;

import com.hardziyevich.application.exception.DaoException;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public enum ConnectionPool {

    INSTANCE();

    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);

    private static final String SQL_DRIVER = "org.postgresql.Driver";
    private static final int DEFAULT_POOL_SIZE = 5;
    private static final int TIMEOUT = 1;

    private String passwordKey;
    private String usernameKey;
    private String urlKey;
    private String poolSize;

    private BlockingQueue<Connection> pool;
    private List<Connection> sourceConnection;

    ConnectionPool() {
    }

    ConnectionPool passwordKey(String passwordKey) {
        this.passwordKey = passwordKey;
        return this;
    }

    ConnectionPool usernameKey(String usernameKey) {
        this.usernameKey = usernameKey;
        return this;
    }

    ConnectionPool urlKey(String urlKey) {
        this.urlKey = urlKey;
        return this;
    }

    ConnectionPool poolSize(String poolSize) {
        this.poolSize = poolSize;
        return this;
    }

    ConnectionPool build() throws DaoException {
        loadDriver();
        initConnectionPool();
        return this;
    }

    private void initConnectionPool() throws DaoException {
        String poolSizeValue = poolSize;
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
     *
     * @return Connection to database.
     */
    public Connection openConnection() throws DaoException {
        Connection connection = null;
        try {
            connection = pool.poll(TIMEOUT, TimeUnit.SECONDS);
            if (!(connection != null && connection.isValid(TIMEOUT))) {
                creatProxyConnection();
                connection = openConnection();
            }
        } catch (InterruptedException | SQLException e) {
            Thread.currentThread().interrupt();
            log.warn("Can not create connection {}", e.getMessage());
            throw new DaoException(e);
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
            Object proxyConnection = Proxy.newProxyInstance(ConnectionPool.class.getClassLoader(), new Class[]{Connection.class}, (proxy, method, args) -> method.getName().equals("close") ? pool.add((Connection) proxy) : method.invoke(connection, args));

            pool.add((Connection) proxyConnection);
            sourceConnection.add(connection);
        } catch (SQLException e) {
            log.warn("Can not create proxy connection {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    public void destroyPool() throws DaoException {
        for (Connection connection : sourceConnection) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.warn("Can not destroyPool {}", e.getMessage());
                throw new DaoException(e);
            }
        }
    }

    private DataSource postgreSqlDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(urlKey);
        dataSource.setUser(usernameKey);
        dataSource.setPassword(passwordKey);
        return dataSource;
    }

    private void loadDriver() throws DaoException {
        try {
            Class.forName(SQL_DRIVER);
        } catch (ClassNotFoundException e) {
            log.warn("Can not loadDriver {}", e.getMessage());
            throw new DaoException(e);
        }
    }

}
