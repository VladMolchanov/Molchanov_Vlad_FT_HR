package by.molchanov.humanresources.command.common;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;

/**
 * Class {@link EmptyCommand} is used for 'null' or empty command.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class EmptyCommand implements ConcreteCommand {
    private static final EmptyCommand EMPTY_COMMAND = new EmptyCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();

    private EmptyCommand() {

    }

    public static EmptyCommand getInstance() {
        return EMPTY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        fillContentCommand.execute(requestHolder);
    }
}
