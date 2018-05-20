package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.SendMessageExecutor;
import by.molchanov.humanresources.executor.impl.SendMessageExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.ANSWER_MESSAGE;
import static by.molchanov.humanresources.command.SessionRequestAttributeName.ASPIRANT_EMAIL;
import static by.molchanov.humanresources.command.SessionRequestAttributeName.MESSAGE_THEME;

/**
 * Class {@link SendRequestAnswerCommand} is used for send answer message to aspirant.
 *
 * @author MolcanovVladislav
 * @see ConcreteCommand
 */
public class SendRequestAnswerCommand implements ConcreteCommand {
    private static final SendRequestAnswerCommand SEND_REPLY_COMMAND = new SendRequestAnswerCommand();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillContentCommand.getInstance();
    private static final SendMessageExecutor SEND_MESSAGE_EXECUTOR = SendMessageExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private SendRequestAnswerCommand() {

    }

    public static SendRequestAnswerCommand getInstance() {
        return SEND_REPLY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String messageTheme = requestHolder.getSingleRequestParameter(FIRST_INDEX, MESSAGE_THEME);
        String message = requestHolder.getSingleRequestParameter(FIRST_INDEX, ANSWER_MESSAGE);
        String aspirantEmail = requestHolder.getSingleRequestParameter(FIRST_INDEX, ASPIRANT_EMAIL);
        try {
            SEND_MESSAGE_EXECUTOR.sendRequestAnswer(messageTheme, message, aspirantEmail);
            FILL_VACANCY_COMMAND.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
