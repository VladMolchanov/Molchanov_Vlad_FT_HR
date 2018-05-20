package by.molchanov.humanresources.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class {@link AddressPageConfiguration} - wrapper class is used for read data about
 * page addresses from property file.
 *
 * @author MolcanovVladislav
 */
public class AddressPageConfiguration {
    private static final AddressPageConfiguration addressPageConfiguration = new AddressPageConfiguration();
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String ADDRESS_PAGE_CONFIGURATION_FILE_NAME = "page_location.properties";
    private static final String MAIN_PAGE_VARIABLE_NAME = "page.location.main";
    private static final String ERROR_PAGE_VARIABLE_NAME = "page.location.error";
    private String mainPageAddress;
    private String errorPageAddress;

    private AddressPageConfiguration() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(ADDRESS_PAGE_CONFIGURATION_FILE_NAME)) {
            properties.load(inputStream);
            mainPageAddress = properties.getProperty(MAIN_PAGE_VARIABLE_NAME);
            errorPageAddress = properties.getProperty(ERROR_PAGE_VARIABLE_NAME);
        } catch (IOException e) {
            LOGGER.error("Properties file opening error!", e);
        }
    }

    public static AddressPageConfiguration getInstance() {
        return addressPageConfiguration;
    }

    public String getMainPageAddress() {
        return mainPageAddress;
    }

    public String getErrorPageAddress() {
        return errorPageAddress;
    }
}
