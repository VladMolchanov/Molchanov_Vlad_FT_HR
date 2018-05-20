package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteCloseExecutor;
import by.molchanov.humanresources.executor.impl.DeleteCloseExecutorImpl;

public class DeleteOldVacancyCommand implements ConcreteCommand {
    private static final DeleteOldVacancyCommand DELETE_OLD_VACANCY_COMMAND = new DeleteOldVacancyCommand();
    private static final DeleteCloseExecutor DELETE_CLOSE_EXECUTOR = DeleteCloseExecutorImpl.getInstance();
    private static final ConcreteCommand FILL_CONTENT_COMMAND = FillContentCommand.getInstance();

    private DeleteOldVacancyCommand() {

    }

    public static DeleteOldVacancyCommand getInstance() {
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
