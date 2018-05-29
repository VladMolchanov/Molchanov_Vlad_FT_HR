package by.molchanov.humanresources.command.common;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.LOCALE;

/**
 * Class {@link EstablishRussianLocaleICommand} is used for set russian language to representation.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class EstablishRussianLocaleICommand implements ConcreteCommand {
    private static final EstablishRussianLocaleICommand EST_RUS_LOC_COMMAND = new EstablishRussianLocaleICommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();

    private static final String RUSSIAN_LOCALE = "ru_RU";

    private EstablishRussianLocaleICommand() {

    }

    public static EstablishRussianLocaleICommand getInstance() {
        return EST_RUS_LOC_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        fillContentCommand.execute(requestHolder);
        requestHolder.addSessionAttribute(LOCALE, RUSSIAN_LOCALE);
    }
}
