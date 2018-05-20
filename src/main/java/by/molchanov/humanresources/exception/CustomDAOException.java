package by.molchanov.humanresources.exception;

public class CustomDAOException extends Exception {
    public CustomDAOException() {
    }

    public CustomDAOException(String message) {
        super(message);
    }

    public CustomDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomDAOException(Throwable cause) {
        super(cause);
    }

    public CustomDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
