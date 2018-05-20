package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteCloseExecutor;
import by.molchanov.humanresources.executor.impl.DeleteCloseExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.USER_ID;

public class DeleteUserCommand implements ConcreteCommand {
    private static final DeleteUserCommand DELETE_USER_COMMAND = new DeleteUserCommand();
    private static final ConcreteCommand FILL_CONTENT_COMMAND = FillContentCommand.getInstance();
    private static final DeleteCloseExecutor DELETE_EXECUTOR = DeleteCloseExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private DeleteUserCommand() {

    }

    public static DeleteUserCommand getInstance() {
        return DELETE_USER_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String requestId = requestHolder.getSingleRequestParameter(FIRST_INDEX, USER_ID);
        try {
            DELETE_EXECUTOR.deleteUser(requestId);
            FILL_CONTENT_COMMAND.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
