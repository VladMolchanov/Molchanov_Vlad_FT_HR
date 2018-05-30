package by.molchanov.humanresources.factory;

import by.molchanov.humanresources.command.ConcreteCommandType;
import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.ResponseType;
import by.molchanov.humanresources.command.common.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.molchanov.humanresources.command.ResponseType.FORWARD;

/**
 * Class {@link OperationFactory} is used for getting certain command and response type.
 *
 * @author Molchanov Vladislav
 */
public class OperationFactory {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final OperationFactory OPERATION_FACTORY = new OperationFactory();

    private OperationFactory() {

    }

    public static OperationFactory getInstance() {
        return OPERATION_FACTORY;
    }

    public ConcreteCommand getConcreteCommand(String command) {
        ConcreteCommand concreteCommand = EmptyCommand.getInstance();
        if (command == null || command.isEmpty()) {
            return concreteCommand;
        }
        try {
            ConcreteCommandType concreteCommandType = ConcreteCommandType.valueOf(command.toUpperCase());
            concreteCommand = concreteCommandType.getConcreteCommandBroker();
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Unknown command!", e);
        }

        return concreteCommand;
    }

    public ResponseType getResponseType(String command) {
        ResponseType responseType = FORWARD;
        if (command == null || command.isEmpty()) {
            return responseType;
        }
        try {
            ConcreteCommandType concreteCommandType = ConcreteCommandType.valueOf(command.toUpperCase());
            responseType = concreteCommandType.getResponseType();
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Unknown command!", e);
        }

        return responseType;
    }
}
