package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillContentExecutor;
import by.molchanov.humanresources.executor.impl.FillContentExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;

public class FillContentCommand implements ConcreteCommand {
    private static final FillContentCommand FILL_VACANCY_COMMAND = new FillContentCommand();
    private static final FillContentExecutor FILL_VACANCY_EXECUTOR = FillContentExecutorImpl.getInstance();

    private FillContentCommand() {

    }

    public static FillContentCommand getInstance() {
        return FILL_VACANCY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<JobVacancy> vacancies;
        List<JobRequest> requests;
        int orgId = 0;
        String userRole = (String) requestHolder.getSessionAttribute(ROLE);
        User user = (User) requestHolder.getSessionAttribute(USER_INFO);
        if (user != null) {
            Organization organization = user.getOrganization();
            orgId = organization.getId();
        }
        try {
            vacancies = FILL_VACANCY_EXECUTOR.fillVacancy(userRole);
            requests = FILL_VACANCY_EXECUTOR.fillRequest(userRole, orgId);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
        requestHolder.addRequestAttribute(REQUEST_LIST, requests);
    }
}
