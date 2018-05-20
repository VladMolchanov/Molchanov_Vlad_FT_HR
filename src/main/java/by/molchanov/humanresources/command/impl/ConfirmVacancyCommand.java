package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.ConfirmExecutor;
import by.molchanov.humanresources.executor.impl.ConfirmExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.VACANCY_ID;

/**
 * Class {@link ConfirmVacancyCommand} is used for confirm the vacancy before publication.
 *
 * @author MolcanovVladislav
 * @see ConcreteCommand
 */
public class ConfirmVacancyCommand implements ConcreteCommand {
    private static final ConfirmVacancyCommand CONFIRM_VACANCY_COMMAND = new ConfirmVacancyCommand();
    private static final ConcreteCommand FILL_CONTENT_COMMAND = FillContentCommand.getInstance();
    private static final ConfirmExecutor CONFIRM_EXECUTOR = ConfirmExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private ConfirmVacancyCommand() {

    }

    public static ConfirmVacancyCommand getIstance() {
        return CONFIRM_VACANCY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String vacancyId = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_ID);
        try {
            CONFIRM_EXECUTOR.confirmVacancy(vacancyId);
            FILL_CONTENT_COMMAND.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
