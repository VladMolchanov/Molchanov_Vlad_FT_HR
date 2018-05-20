package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.exception.CustomExecutorException;

/**
 * Interface {@link SendMessageExecutor} is used for send message.
 *
 * @author MolchanovVladislav
 */
public interface SendMessageExecutor {
    /**
     * Send answer for aspirant request.
     * @param messageTheme theme of message
     * @param message text of message
     * @param aspirantEmail aspirant email
     * @throws CustomExecutorException exception of service level
     */
    void sendRequestAnswer(String messageTheme, String message, String aspirantEmail) throws CustomExecutorException;
}
