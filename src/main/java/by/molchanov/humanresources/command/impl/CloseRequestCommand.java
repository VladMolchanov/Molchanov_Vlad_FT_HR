package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteCloseExecutor;
import by.molchanov.humanresources.executor.impl.DeleteCloseExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.REQUEST_ID;

public class CloseRequestCommand implements ConcreteCommand {
    private static final CloseRequestCommand CLOSE_REQUEST_COMMAND = new CloseRequestCommand();
    private static final ConcreteCommand FILL_CONTENT_COMMAND = FillContentCommand.getInstance();
    private static final DeleteCloseExecutor DELETE_CLOSE_EXECUTOR = DeleteCloseExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private CloseRequestCommand() {

    }

    public static CloseRequestCommand getInstance() {
        return CLOSE_REQUEST_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String requestId = requestHolder.getSingleRequestParameter(FIRST_INDEX, REQUEST_ID);
        try {
            DELETE_CLOSE_EXECUTOR.closeRequest(requestId);
            FILL_CONTENT_COMMAND.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
