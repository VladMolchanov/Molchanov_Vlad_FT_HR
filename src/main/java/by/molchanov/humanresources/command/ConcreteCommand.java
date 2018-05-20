package by.molchanov.humanresources.command;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;

/**
 * Interface {@link ConcreteCommand} is used for all commands.
 * Contains common method 'execute'.
 *
 * @author MolcanovVladislav
 */
public interface ConcreteCommand {
    /**
     * Does different work to handle command
     * @param requestHolder Object of {@link RequestHolder}, which contains all servlet request content.
     * @throws CustomBrokerException Custom exception of command level.
     */
    void execute(RequestHolder requestHolder) throws CustomBrokerException;
}
