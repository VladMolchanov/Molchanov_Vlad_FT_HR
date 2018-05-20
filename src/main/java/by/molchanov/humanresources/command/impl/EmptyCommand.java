package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.impl.SendMessageExecutorImpl;

public class EmptyCommand implements ConcreteCommand {
    private static final EmptyCommand EMPTY_COMMAND = new EmptyCommand();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillContentCommand.getInstance();

    private EmptyCommand() {

    }

    public static EmptyCommand getInstance() {
        return EMPTY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        FILL_VACANCY_COMMAND.execute(requestHolder);
    }
}