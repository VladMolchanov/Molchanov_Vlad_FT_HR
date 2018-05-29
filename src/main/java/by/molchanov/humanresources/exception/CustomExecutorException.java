package by.molchanov.humanresources.exception;

/**
 * Class {@link CustomExecutorException} is used to store service level exceptions.
 *
 * @author Molchanov Vladislav
 * @see Exception
 */
public class CustomExecutorException extends Exception {
    public CustomExecutorException() {
    }

    public CustomExecutorException(String message) {
        super(message);
    }

    public CustomExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomExecutorException(Throwable cause) {
        super(cause);
    }

    public CustomExecutorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
