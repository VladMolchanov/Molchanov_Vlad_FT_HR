package by.molchanov.humanresources.command.admin;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteCloseExecutor;
import by.molchanov.humanresources.executor.impl.DeleteCloseExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.VACANCY_ID;

/**
 * Class {@link DeleteVacancyCommand} is used for delete unacceptable vacancy from system.
 *
 * @author Molchanov Vladislav
 * @see  ConcreteCommand
 */
public class DeleteVacancyCommand implements ConcreteCommand {
    private static final DeleteVacancyCommand DELETE_VACANCY_COMMAND = new DeleteVacancyCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();
    private DeleteCloseExecutor deleteCloseExecutor = DeleteCloseExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private DeleteVacancyCommand() {

    }

    public static DeleteVacancyCommand getInstance() {
        return DELETE_VACANCY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String vacancyId = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_ID);
        try {
            deleteCloseExecutor.deleteVacancy(vacancyId);
            fillContentCommand.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
