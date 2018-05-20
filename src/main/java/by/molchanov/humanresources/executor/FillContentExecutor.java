package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomExecutorException;

import java.util.List;

public interface FillContentExecutor {
    List<JobVacancy> fillVacancy(String userRole) throws CustomExecutorException;
    List<JobRequest> fillRequest(String userRole, int organizationId) throws CustomExecutorException;
}
