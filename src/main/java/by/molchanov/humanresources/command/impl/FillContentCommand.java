package by.molchanov.humanresources.command.impl;

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
 * @author MolcanovVladislav
 * @see ConcreteCommand
 */
public class FillContentCommand implements ConcreteCommand {
    private static final FillContentCommand FILL_VACANCY_COMMAND = new FillContentCommand();
    private static final FillContentExecutor FILL_CONTENT_EXECUTOR = FillContentExecutorImpl.getInstance();
    private static final FilterExecutor FILTER_EXECUTOR = FilterExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private FillContentCommand() {

    }

    public static FillContentCommand getInstance() {
        return FILL_VACANCY_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<JobVacancy> vacancies;
        List<JobRequest> requests;
        String emptySearchField = "";
        int vacanciesCount;
        int requestsCount;
        int orgId = 0;
        int startVacancyNumber;
        int startRequestNumber;
        int vacanciesQuantity;
        int requestsQuantity;
        FilterDataDTO filterDataDTO;
        String userRole = (String) requestHolder.getSessionAttribute(ROLE);
        User user = (User) requestHolder.getSessionAttribute(USER_INFO);
        Boolean vacFilterFlag = (Boolean) requestHolder.getSessionAttribute(VAC_FILTER_FLAG);
        Boolean reqFilterFlag = (Boolean) requestHolder.getSessionAttribute(REQUEST_FILTER_FLAG);
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
            if (vacFilterFlag != null && vacFilterFlag) {
                filterDataDTO = (FilterDataDTO) requestHolder.getSessionAttribute(VAC_FILTER_DATA);
                vacancies = FILTER_EXECUTOR.filterVacancy(filterDataDTO, userRole, startVacancyNumber, vacanciesQuantity);
                vacanciesCount = FILL_CONTENT_EXECUTOR.findVacanciesCount(userRole, filterDataDTO.getSearchField());
            } else {
                vacancies = FILL_CONTENT_EXECUTOR.fillVacancy(userRole, emptySearchField, startVacancyNumber, vacanciesQuantity);
                vacanciesCount = FILL_CONTENT_EXECUTOR.findVacanciesCount(userRole, emptySearchField);
            }
            if (reqFilterFlag != null && reqFilterFlag) {
                filterDataDTO = (FilterDataDTO) requestHolder.getSessionAttribute(REQUEST_FILTER_DATA);
                requests = FILTER_EXECUTOR.filterRequest(filterDataDTO, userRole, startRequestNumber, requestsQuantity);
                requestsCount = FILL_CONTENT_EXECUTOR.findRequestsCount(userRole, orgId, emptySearchField);
            } else {
                requests = FILL_CONTENT_EXECUTOR.fillRequest(userRole, orgId, emptySearchField, startRequestNumber, requestsQuantity);
                requestsCount = FILL_CONTENT_EXECUTOR.findRequestsCount(userRole, orgId, emptySearchField);
            }
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(VACANCY_LIST, vacancies);
        requestHolder.addRequestAttribute(REQUEST_LIST, requests);
        requestHolder.addRequestAttribute(START_VACANCY_NUMBER, startVacancyNumber);
        requestHolder.addRequestAttribute(VACANCIES_QUANTITY, vacanciesQuantity);
        requestHolder.addRequestAttribute(VACANCIES_COUNT, vacanciesCount);
        requestHolder.addRequestAttribute(START_REQUEST_NUMBER, startRequestNumber);
        requestHolder.addRequestAttribute(REQUESTS_QUANTITY, requestsQuantity);
        requestHolder.addRequestAttribute(REQUESTS_COUNT, requestsCount);
        System.out.println(requestsCount);
    }
}
