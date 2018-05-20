package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.LOCALE;

/**
 * Class {@link EstablishRussianLocaleICommand} is used for set russian language to representation.
 *
 * @author MolcanovVladislav
 * @see ConcreteCommand
 */
public class EstablishRussianLocaleICommand implements ConcreteCommand {
    private static final EstablishRussianLocaleICommand EST_RUS_LOC_COMMAND = new EstablishRussianLocaleICommand();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillContentCommand.getInstance();
    private static final String RUSSIAN_LOCALE = "ru_RU";

    private EstablishRussianLocaleICommand() {

    }

    public static EstablishRussianLocaleICommand getInstance() {
        return EST_RUS_LOC_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        FILL_VACANCY_COMMAND.execute(requestHolder);
        requestHolder.addSessionAttribute(LOCALE, RUSSIAN_LOCALE);
    }
}
