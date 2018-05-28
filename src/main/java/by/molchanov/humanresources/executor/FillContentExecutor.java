package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.entity.*;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;

import java.util.List;

/**
 * Interface {@link FillContentExecutor} is used for fill page content.
 *
 * @author MolchanovVladislav
 */
public interface FillContentExecutor {
    /**
     * Fill page vacancy content depending on user role in system
     * @param userRole user role in system
     * @return collection of vacancies
     * @throws CustomExecutorException exception of service level
     */
    List<JobVacancy> fillVacancy(String userRole, String searchField, int startVacancyNumber,
                                 int vacanciesQuantity) throws CustomExecutorException;
    /**
     * Fill page request content depending on user role in system
     * @param userRole user role in system
     * @param organizationId organization id of director
     * @return collection of requests
     * @throws CustomExecutorException exception of service level
     */
    List<JobRequest> fillRequest(String userRole, int organizationId, String searchField,
                                 int startRequestNumber, int requestsQuantity) throws CustomExecutorException;

    List<User> fillUser(String userRole, int startUserNumber, int usersQuantity) throws CustomExecutorException;

    List<Organization> fillOrganization(int startOrganizationNumber, int organizationsQuantity) throws CustomExecutorException;

    int findVacanciesCount(String userRole, String searchField) throws CustomExecutorException;

    int findRequestsCount(String userRole, int orgId, String searchField) throws CustomExecutorException;

    int findUsersCount(String userRole) throws CustomExecutorException;

    int findOrganizationsCount() throws CustomExecutorException;
}
