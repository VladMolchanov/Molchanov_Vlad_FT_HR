package by.molchanov.humanresources.exception;

/**
 * Class {@link CustomBrokerException} is used to store command level exceptions.
 *
 * @author Molchanov Vladislav
 * @see Exception
 */
public class CustomBrokerException extends Exception {
    public CustomBrokerException() {
    }

    public CustomBrokerException(String message) {
        super(message);
    }

    public CustomBrokerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomBrokerException(Throwable cause) {
        super(cause);
    }

    public CustomBrokerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
