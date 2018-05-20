package by.molchanov.humanresources.exception;

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
