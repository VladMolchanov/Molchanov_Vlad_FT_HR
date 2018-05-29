package by.molchanov.humanresources.command.common;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;

import by.molchanov.humanresources.exception.CustomBrokerException;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.LOCALE;

/**
 * Class {@link EstablishBelorussianLocaleCommand} is used for set belorussian language to representation.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class EstablishBelorussianLocaleCommand implements ConcreteCommand {
    private static final EstablishBelorussianLocaleCommand EST_BEL_LOC_COMMAND = new EstablishBelorussianLocaleCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();

    private static final String BELORUSSIAN_LOCALE = "be_BY";

    private EstablishBelorussianLocaleCommand() {

    }

    public static EstablishBelorussianLocaleCommand getInstance() {
        return EST_BEL_LOC_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        fillContentCommand.execute(requestHolder);
        requestHolder.addSessionAttribute(LOCALE, BELORUSSIAN_LOCALE);
    }
}
