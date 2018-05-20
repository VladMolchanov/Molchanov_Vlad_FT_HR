package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.executor.LogOutExecutor;
import by.molchanov.humanresources.executor.impl.LogOutExecutorImpl;

import java.util.List;

public class LogOutCommand implements ConcreteCommand {
    private static final LogOutCommand LOG_OUT_COMMAND = new LogOutCommand();
    private static final LogOutExecutor LOG_OUT_EXECUTOR = LogOutExecutorImpl.getInstance();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillContentCommand.getInstance();

    private LogOutCommand() {

    }

    public static LogOutCommand getInstance() {
        return LOG_OUT_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<String> attributeForDelete;
        attributeForDelete = LOG_OUT_EXECUTOR.logOut();
        requestHolder.removeSessionAttribute(attributeForDelete.toArray(new String[attributeForDelete.size()]));
        FILL_VACANCY_COMMAND.execute(requestHolder);
    }
}
