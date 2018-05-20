package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomExecutorException;

import java.util.List;

/**
 * Interface {@link FilterExecutor} is used for searching and filter to content.
 *
 * @author MolchanovVladislav
 */
public interface FilterExecutor {
    /**
     * Filter and searching to vacancies
     * @param sortColumn column, which used for sorting
     * @param sortDirectionType type of sorting (increase, decrease)
     * @param searchField word for search
     * @param userRole role of user in system
     * @return collection of vacancies
     * @throws CustomExecutorException exception of service level
     */
    List<JobVacancy> filterVacancy(String sortColumn, String sortDirectionType, String searchField, String userRole) throws CustomExecutorException;
    /**
     * Filter and searching to requests
     * @param sortColumn column, which used for sorting
     * @param sortDirectionType type of sorting (increase, decrease)
     * @param searchField word for search
     * @param userRole role of user in system
     * @param orgId organization id of director in system
     * @return collection of requests
     * @throws CustomExecutorException exception of service level
     */
    List<JobRequest> filterRequest(String sortColumn, String sortDirectionType, String searchField, String userRole, int orgId) throws CustomExecutorException;
}
