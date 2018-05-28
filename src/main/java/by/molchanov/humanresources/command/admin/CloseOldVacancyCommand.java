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
 * @author MolcanovVladislav
 * @see ConcreteCommand
 */
public class CloseOldVacancyCommand implements ConcreteCommand {
    private static final CloseOldVacancyCommand DELETE_OLD_VACANCY_COMMAND = new CloseOldVacancyCommand();
    private static final DeleteCloseExecutor DELETE_CLOSE_EXECUTOR = DeleteCloseExecutorImpl.getInstance();
    private static final ConcreteCommand FILL_CONTENT_COMMAND = FillContentCommand.getInstance();

    private CloseOldVacancyCommand() {

    }

    public static CloseOldVacancyCommand getInstance() {
        return DELETE_OLD_VACANCY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        try {
            DELETE_CLOSE_EXECUTOR.closeOldVacancy();
            FILL_CONTENT_COMMAND.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
