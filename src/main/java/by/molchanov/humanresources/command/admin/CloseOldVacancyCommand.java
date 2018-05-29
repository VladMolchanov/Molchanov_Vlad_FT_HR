package by.molchanov.humanresources.command.admin;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteCloseExecutor;
import by.molchanov.humanresources.executor.impl.DeleteCloseExecutorImpl;

/**
 * Class {@link CloseOldVacancyCommand} is used for close old vacancy (published more than 15 days ago).
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class CloseOldVacancyCommand implements ConcreteCommand {
    private static final CloseOldVacancyCommand CLOSE_OLD_VACANCY_COMMAND = new CloseOldVacancyCommand();
    private DeleteCloseExecutor deleteCloseExecutor = DeleteCloseExecutorImpl.getInstance();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();

    private CloseOldVacancyCommand() {

    }

    public static CloseOldVacancyCommand getInstance() {
        return CLOSE_OLD_VACANCY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        try {
            deleteCloseExecutor.closeOldVacancy();
            fillContentCommand.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
