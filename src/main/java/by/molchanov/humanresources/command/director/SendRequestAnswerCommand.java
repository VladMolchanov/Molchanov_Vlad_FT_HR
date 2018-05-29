package by.molchanov.humanresources.command.director;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.MessageDataDTO;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.ConfirmExecutor;
import by.molchanov.humanresources.executor.SendMessageExecutor;
import by.molchanov.humanresources.executor.impl.ConfirmExecutorImpl;
import by.molchanov.humanresources.executor.impl.SendMessageExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;

/**
 * Class {@link SendRequestAnswerCommand} is used for send answer message to aspirant.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class SendRequestAnswerCommand implements ConcreteCommand {
    private static final SendRequestAnswerCommand SEND_REPLY_COMMAND = new SendRequestAnswerCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();
    private SendMessageExecutor sendMessageExecutor = SendMessageExecutorImpl.getInstance();
    private ConfirmExecutor confirmExecutor = ConfirmExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private SendRequestAnswerCommand() {

    }

    public static SendRequestAnswerCommand getInstance() {
        return SEND_REPLY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String requestId = requestHolder.getSingleRequestParameter(FIRST_INDEX, REQUEST_ID);
        String messageTheme = requestHolder.getSingleRequestParameter(FIRST_INDEX, MESSAGE_THEME);
        String message = requestHolder.getSingleRequestParameter(FIRST_INDEX, ANSWER_MESSAGE);
        String aspirantEmail = requestHolder.getSingleRequestParameter(FIRST_INDEX, ASPIRANT_EMAIL);
        String vacName = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_NAME);
        MessageDataDTO messageDataDTO = new MessageDataDTO();
        messageDataDTO.setMessageText(message);
        messageDataDTO.setMessageTheme(messageTheme);
        messageDataDTO.setReceiverEmail(aspirantEmail);
        messageDataDTO.setVacancyName(vacName);
        try {
            sendMessageExecutor.sendRequestAnswer(messageDataDTO);
            confirmExecutor.approveRequest(requestId);
            fillContentCommand.execute(requestHolder);
            requestHolder.addRequestAttribute(INFO_MESSAGE, messageDataDTO.getInfoMessage());
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
