package by.molchanov.humanresources.exception;

/**
 * Class {@link CustomDAOException} is used to store dao level exceptions.
 *
 * @author Molchanov Vladislav
 * @see Exception
 */
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
