package by.molchanov.humanresources.command.common;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;

/**
 * Class {@link EmptyCommand} is used for 'null' or empty command.
 *
 * @author MolcanovVladislav
 * @see ConcreteCommand
 */
public class EmptyCommand implements ConcreteCommand {
    private static final EmptyCommand EMPTY_COMMAND = new EmptyCommand();
    private static final ConcreteCommand FILL_CONTENT_COMMAND = FillContentCommand.getInstance();

    private EmptyCommand() {

    }

    public static EmptyCommand getInstance() {
        return EMPTY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        FILL_CONTENT_COMMAND.execute(requestHolder);
    }
}
