package by.molchanov.humanresources.command.director;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteCloseExecutor;
import by.molchanov.humanresources.executor.impl.DeleteCloseExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.REQUEST_ID;

/**
 * Class {@link CloseRequestCommand} is used for rejecting aspirant request (set another request status)  .
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class CloseRequestCommand implements ConcreteCommand {
    private static final CloseRequestCommand CLOSE_REQUEST_COMMAND = new CloseRequestCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();
    private DeleteCloseExecutor deleteCloseExecutor = DeleteCloseExecutorImpl.getInstance();

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
            deleteCloseExecutor.closeRequest(requestId);
            fillContentCommand.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
