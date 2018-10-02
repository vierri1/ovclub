package ru.openvoleyballclub.repository.connection_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerJdbcImpl implements ConnectionManager {
    private static volatile ConnectionManager connectionManager;

    private ConnectionManagerJdbcImpl() {
    }

    public static ConnectionManager getInstance() {
        ConnectionManager localInstance = connectionManager;
        if (localInstance == null) {
            synchronized (ConnectionManagerJdbcImpl.class) {
                localInstance = connectionManager;
                if (localInstance == null) {
                    connectionManager = new ConnectionManagerJdbcImpl();
                    localInstance = connectionManager;
                }
            }
        }
        return localInstance;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ovl", "postgres", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
