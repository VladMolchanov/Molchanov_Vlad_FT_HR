package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;

import by.molchanov.humanresources.exception.CustomBrokerException;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.LOCALE;

public class EstablishBelorussianLocaleCommand implements ConcreteCommand {
    private static final EstablishBelorussianLocaleCommand EST_BEL_LOC_COMMAND = new EstablishBelorussianLocaleCommand();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillContentCommand.getInstance();
    private static final String BELORUSSIAN_LOCALE = "be_BY";

    private EstablishBelorussianLocaleCommand() {

    }

    public static EstablishBelorussianLocaleCommand getInstance() {
        return EST_BEL_LOC_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        FILL_VACANCY_COMMAND.execute(requestHolder);
        requestHolder.addSessionAttribute(LOCALE, BELORUSSIAN_LOCALE);
    }
}
