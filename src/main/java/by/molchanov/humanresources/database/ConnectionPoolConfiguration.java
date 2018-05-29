package by.molchanov.humanresources.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class {@link ConnectionPoolConfiguration} - wrapper class is used for read data about
 * database configuration from property file.
 *
 * @author Molchanov Vladislav
 */
class ConnectionPoolConfiguration {
    private static final ConnectionPoolConfiguration connectionPoolConfiguration = new ConnectionPoolConfiguration();
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String DB_CONFIGURATION_FILE_NAME = "db_configuration.properties";
    private String user;
    private String password;
    private int poolSize;
    private String url;

    private ConnectionPoolConfiguration() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DB_CONFIGURATION_FILE_NAME)) {
            properties.load(inputStream);
            user = properties.getProperty("db.configuration.user");
            password = properties.getProperty("db.configuration.password");
            poolSize = Integer.parseInt(properties.getProperty("db.configuration.pool.size"));
            url = properties.getProperty("db.configuration.url");
        } catch (IOException e) {
            LOGGER.error("Properties file opening error!", e);
        }
    }

    static ConnectionPoolConfiguration getInstance() {
        return connectionPoolConfiguration;
    }

    String getUser() {
        return user;
    }

    String getPassword() {
        return password;
    }

    int getPoolSize() {
        return poolSize;
    }

    String getUrl() {
        return url;
    }
}
