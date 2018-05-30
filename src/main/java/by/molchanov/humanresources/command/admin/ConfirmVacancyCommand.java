package by.molchanov.humanresources.command.admin;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.ConfirmExecutor;
import by.molchanov.humanresources.executor.impl.ConfirmExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.VACANCY_ID;

/**
 * Class {@link ConfirmVacancyCommand} is used for confirm the vacancy before publication.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class ConfirmVacancyCommand implements ConcreteCommand {
    private static final ConfirmVacancyCommand CONFIRM_VACANCY_COMMAND = new ConfirmVacancyCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();
    private ConfirmExecutor confirmExecutor = ConfirmExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private ConfirmVacancyCommand() {

    }

    public static ConfirmVacancyCommand getInstance() {
        return CONFIRM_VACANCY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String vacancyId = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_ID);
        try {
            confirmExecutor.confirmVacancy(vacancyId);
            fillContentCommand.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
