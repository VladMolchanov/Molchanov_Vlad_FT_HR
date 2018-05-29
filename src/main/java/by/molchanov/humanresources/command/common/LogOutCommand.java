package by.molchanov.humanresources.command.common;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.executor.LogOutExecutor;
import by.molchanov.humanresources.executor.impl.LogOutExecutorImpl;

import java.util.List;

/**
 * Class {@link LogOutCommand} is used for user logout from system.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class LogOutCommand implements ConcreteCommand {
    private static final LogOutCommand LOG_OUT_COMMAND = new LogOutCommand();
    private LogOutExecutor logOutExecutor = LogOutExecutorImpl.getInstance();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();

    private LogOutCommand() {

    }

    public static LogOutCommand getInstance() {
        return LOG_OUT_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<String> attributeForDelete;
        attributeForDelete = logOutExecutor.logOut();
        requestHolder.removeSessionAttribute(attributeForDelete.toArray(new String[attributeForDelete.size()]));
        fillContentCommand.execute(requestHolder);
    }
}
