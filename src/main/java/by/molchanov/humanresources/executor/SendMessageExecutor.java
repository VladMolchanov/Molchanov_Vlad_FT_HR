package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.dto.MessageDataDTO;
import by.molchanov.humanresources.exception.CustomExecutorException;

/**
 * Interface {@link SendMessageExecutor} is used for send message.
 *
 * @author MolchanovVladislav
 */
public interface SendMessageExecutor {
    /**
     * Send answer for aspirant request.
     * @param messageDataDTO object, which contains all data for message sending
     * @throws CustomExecutorException exception of service level
     */
    void sendRequestAnswer(MessageDataDTO messageDataDTO) throws CustomExecutorException;
}
