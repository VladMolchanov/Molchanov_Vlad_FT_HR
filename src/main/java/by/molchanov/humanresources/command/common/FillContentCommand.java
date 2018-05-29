package by.molchanov.humanresources.command.common;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.FilterDataDTO;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillContentExecutor;
import by.molchanov.humanresources.executor.FilterExecutor;
import by.molchanov.humanresources.executor.impl.FillContentExecutorImpl;
import by.molchanov.humanresources.executor.impl.FilterExecutorImpl;

import java.util.List;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;

/**
 * Class {@link FillContentCommand} is used for create page content, such as request, vacancies and other.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class FillContentCommand implements ConcreteCommand {
    private static final FillContentCommand FILL_VACANCY_COMMAND = new FillContentCommand();
    private FillContentExecutor fillContentExecutor = FillContentExecutorImpl.getInstance();
    private FilterExecutor filterExecutor = FilterExecutorImpl.getInstance();

    private static final String ROLE_ADMIN = "admin";
    private static final String ROLE_DIRECTOR = "director";
    private static final String ROLE_ASPIRANT = "aspirant";
    private static final String ROLE_GUEST = "guest";

    private static final int FIRST_INDEX = 0;

    private FillContentCommand() {

    }

    public static FillContentCommand getInstance() {
        return FILL_VACANCY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String userRole = (String) requestHolder.getSessionAttribute(ROLE);
        if (userRole == null) {
            userRole = ROLE_GUEST;
        }
        switch (userRole) {
            case ROLE_ASPIRANT:
                jobVacancyContent(requestHolder);
                break;
            case ROLE_DIRECTOR:
                jobVacancyContent(requestHolder);
                jobRequestContent(requestHolder);
                break;
            case ROLE_ADMIN:
                jobVacancyContent(requestHolder);
                userContent(requestHolder);
                organizationContent(requestHolder);
                break;
            default:
                jobVacancyContent(requestHolder);
                break;
        }
    }

    private void jobRequestContent(RequestHolder requestHolder) throws CustomBrokerException {
        List<JobRequest> requests;
        String emptySearchField = "";
        int requestsCount;
        int orgId = 0;
        int startRequestNumber;
        int requestsQuantity;
        FilterDataDTO filterDataDTO;
        String userRole = (String) requestHolder.getSessionAttribute(ROLE);
        User user = (User) requestHolder.getSessionAttribute(USER_INFO);
        Boolean reqFilterFlag = (Boolean) requestHolder.getSessionAttribute(REQUEST_FILTER_FLAG);
        try {
            startRequestNumber = Integer.parseInt(requestHolder.getSingleRequestParameter(FIRST_INDEX, START_REQUEST_NUMBER));
        } catch (NumberFormatException | NullPointerException e) {
            startRequestNumber = 0;
        }
        try {
            requestsQuantity = Integer.parseInt(requestHolder.getSingleRequestParameter(FIRST_INDEX, REQUESTS_QUANTITY));
        } catch (NumberFormatException | NullPointerException e) {
            requestsQuantity = 10;
        }
        if (user != null) {
            Organization organization = user.getOrganization();
            orgId = organization.getId();
        }
        try {
            if (reqFilterFlag != null && reqFilterFlag) {
                filterDataDTO = (FilterDataDTO) requestHolder.getSessionAttribute(REQUEST_FILTER_DATA);
                requests = filterExecutor.filterRequest(filterDataDTO, userRole, startRequestNumber, requestsQuantity);
                requestsCount = fillContentExecutor.findRequestsCount(userRole, orgId, emptySearchField);
            } else {
                requests = fillContentExecutor.fillRequest(userRole, orgId, emptySearchField, startRequestNumber, requestsQuantity);
                requestsCount = fillContentExecutor.findRequestsCount(userRole, orgId, emptySearchField);
            }
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(REQUEST_LIST, requests);
        requestHolder.addRequestAttribute(START_REQUEST_NUMBER, startRequestNumber);
        requestHolder.addRequestAttribute(REQUESTS_QUANTITY, requestsQuantity);
        requestHolder.addRequestAttribute(REQUESTS_COUNT, requestsCount);
    }

    private void jobVacancyContent(RequestHolder requestHolder) throws CustomBrokerException {
        List<JobVacancy> vacancies;
        String emptySearchField = "";
        int vacanciesCount;
        int startVacancyNumber;
        int vacanciesQuantity;
        FilterDataDTO filterDataDTO;
        String userRole = (String) requestHolder.getSessionAttribute(ROLE);
        Boolean vacFilterFlag = (Boolean) requestHolder.getSessionAttribute(VAC_FILTER_FLAG);
        try {
            startVacancyNumber = Integer.parseInt(requestHolder.getSingleRequestParameter(FIRST_INDEX, START_VACANCY_NUMBER));
        } catch (NumberFormatException | NullPointerException e) {
            startVacancyNumber = 0;
        }
        try {
            vacanciesQuantity = Integer.parseInt(requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCIES_QUANTITY));
        } catch (NumberFormatException | NullPointerException e) {
            vacanciesQuantity = 10;
        }
        try {
            if (vacFilterFlag != null && vacFilterFlag) {
                filterDataDTO = (FilterDataDTO) requestHolder.getSessionAttribute(VAC_FILTER_DATA);
                vacancies = filterExecutor.filterVacancy(filterDataDTO, userRole, startVacancyNumber, vacanciesQuantity);
                vacanciesCount = fillContentExecutor.findVacanciesCount(userRole, filterDataDTO.getSearchField());
            } else {
                vacancies = fillContentExecutor.fillVacancy(userRole, emptySearchField, startVacancyNumber, vacanciesQuantity);
                vacanciesCount = fillContentExecutor.findVacanciesCount(userRole, emptySearchField);
            }
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
        requestHolder.addRequestAttribute(START_VACANCY_NUMBER, startVacancyNumber);
        requestHolder.addRequestAttribute(VACANCIES_QUANTITY, vacanciesQuantity);
        requestHolder.addRequestAttribute(VACANCIES_COUNT, vacanciesCount);
    }

    private void userContent(RequestHolder requestHolder) throws CustomBrokerException {
        List<User> users;
        int usersCount;
        int startUserNumber;
        int usersQuantity;
        String userRole = (String) requestHolder.getSessionAttribute(ROLE);
        try {
            startUserNumber = Integer.parseInt(requestHolder.getSingleRequestParameter(FIRST_INDEX, START_USER_NUMBER));
        } catch (NumberFormatException | NullPointerException e) {
            startUserNumber = 0;
        }
        try {
            usersQuantity = Integer.parseInt(requestHolder.getSingleRequestParameter(FIRST_INDEX, USERS_QUANTITY));
        } catch (NumberFormatException | NullPointerException e) {
            usersQuantity = 10;
        }
        try {
                users = fillContentExecutor.fillUser(userRole, startUserNumber, usersQuantity);
                usersCount = fillContentExecutor.findUsersCount(userRole);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(USER_LIST, users);
        requestHolder.addRequestAttribute(START_USER_NUMBER, startUserNumber);
        requestHolder.addRequestAttribute(USERS_QUANTITY, usersQuantity);
        requestHolder.addRequestAttribute(USERS_COUNT, usersCount);
    }

    private void organizationContent(RequestHolder requestHolder) throws CustomBrokerException {
        List<Organization> organizations;
        int organizationsCount;
        int startOrganizationNumber;
        int organizationsQuantity;
        try {
            startOrganizationNumber = Integer.parseInt(requestHolder.getSingleRequestParameter(FIRST_INDEX, START_USER_NUMBER));
        } catch (NumberFormatException | NullPointerException e) {
            startOrganizationNumber = 0;
        }
        try {
            organizationsQuantity = Integer.parseInt(requestHolder.getSingleRequestParameter(FIRST_INDEX, USERS_QUANTITY));
        } catch (NumberFormatException | NullPointerException e) {
            organizationsQuantity = 10;
        }
        try {
            organizations = fillContentExecutor.fillOrganization(startOrganizationNumber, organizationsQuantity);
            organizationsCount = fillContentExecutor.findOrganizationsCount();
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(ORGANIZATION_LIST, organizations);
        requestHolder.addRequestAttribute(START_ORGANIZATION_NUMBER, startOrganizationNumber);
        requestHolder.addRequestAttribute(ORGANIZATIONS_QUANTITY, organizationsQuantity);
        requestHolder.addRequestAttribute(ORGANIZATIONS_COUNT, organizationsCount);
    }
}
