package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.exception.CustomExecutorException;

public interface SendMessageExecutor {
    void sendRequestAnswer(String messageTheme, String message, String aspirantEmail) throws CustomExecutorException;
}
