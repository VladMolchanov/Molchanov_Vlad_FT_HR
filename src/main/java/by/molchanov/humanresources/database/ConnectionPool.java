package by.molchanov.humanresources.database;

import by.molchanov.humanresources.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private static ConnectionPool connectionPool;
    private BlockingQueue<Connection> connectionQueue;
    private static AtomicBoolean instanceExist = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private static final ConnectionPoolConfiguration CONFIGURATION = ConnectionPoolConfiguration.getInstance();

    private ConnectionPool() throws CustomException {
        connectionQueue = new ArrayBlockingQueue<>(CONFIGURATION.getPoolSize());
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new CustomException("SQL driver error!", e);
        }
        for (int i = 0; i < CONFIGURATION.getPoolSize(); i++) {
            addConnection();
        }
    }

    public static ConnectionPool getInstance() {
        if (!instanceExist.get()) {
            lock.lock();
            try {
                if (connectionPool == null) {
                    connectionPool = new ConnectionPool();
                    instanceExist.getAndSet(true);
                }
            } catch (CustomException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                lock.unlock();
            }
        }
        return connectionPool;
    }

    private void addConnection() {
        try {
            Connection connection = DriverManager.getConnection(CONFIGURATION.getUrl(), CONFIGURATION.getUser(),
                    CONFIGURATION.getPassword());
            this.connectionQueue.put(connection);
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("Connection wasn't add into connection pool", e);
        }
    }

    public Connection takeConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            LOGGER.error("Couldn't take connection from connection pool", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        try {
            connectionQueue.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Couldn't close connection", e);
            Thread.currentThread().interrupt();
        }
    }

    public void closePool() {
        try {
            for (int i = 0; i < CONFIGURATION.getPoolSize(); i++) {
                connectionQueue.take().close();
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("Couldn't close connection pool", e);
            Thread.currentThread().interrupt();
        }
    }
}