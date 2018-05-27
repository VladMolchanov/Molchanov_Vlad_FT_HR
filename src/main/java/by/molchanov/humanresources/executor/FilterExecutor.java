package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.dto.FilterDataDTO;
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
     * @param filterDataDTO object, which consist all data for sorting and searching
     * @return collection of vacancies
     * @throws CustomExecutorException exception of service level
     */
    List<JobVacancy> filterVacancy(FilterDataDTO filterDataDTO, String userRole, int startVacancyNumber,
                                   int vacanciesQuantity) throws CustomExecutorException;
    /**
     * Filter and searching to requests
     * @param filterDataDTO object, which consist all data for sorting and searching
     * @return collection of requests
     * @throws CustomExecutorException exception of service level
     */
    List<JobRequest> filterRequest(FilterDataDTO filterDataDTO, String userRole,
                                   int startRequestNumber, int requestsQuantity) throws CustomExecutorException;
}
