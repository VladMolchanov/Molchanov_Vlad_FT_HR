package by.molchanov.humanresources.command.director;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteCloseExecutor;
import by.molchanov.humanresources.executor.impl.DeleteCloseExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.VACANCY_ID;

/**
 * Class {@link CloseVacancyCommand} is used for closing job vacancy (set another vacancy status).
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class CloseVacancyCommand implements ConcreteCommand {
    private static final CloseVacancyCommand CLOSE_VACANCY_COMMAND = new CloseVacancyCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();
    private DeleteCloseExecutor deleteCloseExecutor = DeleteCloseExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private CloseVacancyCommand() {

    }

    public static CloseVacancyCommand getInstance() {
        return CLOSE_VACANCY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String vacancyId = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_ID);
        try {
            deleteCloseExecutor.closeVacancy(vacancyId);
            fillContentCommand.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
