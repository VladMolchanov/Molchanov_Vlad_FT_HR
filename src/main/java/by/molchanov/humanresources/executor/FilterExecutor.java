package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomExecutorException;

import java.util.List;

public interface FilterExecutor {
    List<JobVacancy> filterVacancy(String sortColumn, String sortDirectionType, String searchField, String userRole) throws CustomExecutorException;
    List<JobRequest> filterRequest(String sortColumn, String sortDirectionType, String searchField, String userRole, int orgId) throws CustomExecutorException;
}
