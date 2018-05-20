package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FilterExecutor;
import by.molchanov.humanresources.executor.impl.FilterExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;

public class VacancyFilterCommand implements ConcreteCommand {
    private static final VacancyFilterCommand VACANCY_FILTER_COMMAND = new VacancyFilterCommand();
    private static final FilterExecutor VACANCY_FILTER_EXECUTOR = FilterExecutorImpl.getInstance();
    private static final ConcreteCommand FILL_CONTENT_COMMAND = FillContentCommand.getInstance();

    private static final int FIRST_INDEX = 0;

    private VacancyFilterCommand() {

    }

    public static VacancyFilterCommand getInstance() {
        return VACANCY_FILTER_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String sortColumn = requestHolder.getSingleRequestParameter(FIRST_INDEX, SORT_COL);
        String sortDirectionType = requestHolder.getSingleRequestParameter(FIRST_INDEX, SORT_TYPE);
        String searchField = requestHolder.getSingleRequestParameter(FIRST_INDEX, SEARCH_FIELD);
        String userRole = (String) requestHolder.getSessionAttribute(ROLE);
        List<JobVacancy> vacancies;
        try {
            FILL_CONTENT_COMMAND.execute(requestHolder);
            vacancies = VACANCY_FILTER_EXECUTOR.filterVacancy(sortColumn, sortDirectionType, searchField, userRole);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
    }
}
