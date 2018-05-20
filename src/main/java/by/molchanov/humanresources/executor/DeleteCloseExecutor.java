package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.exception.CustomExecutorException;

public interface DeleteCloseExecutor {
    void deleteVacancy(String vacancyId) throws CustomExecutorException;
    void deleteRequest(String requestId) throws CustomExecutorException;
    void deleteUser(String userId) throws CustomExecutorException;
    void closeOldVacancy() throws CustomExecutorException;
    void closeRequest(String requestId) throws CustomExecutorException;
    void closeVacancy(String vacancyId) throws CustomExecutorException;
}
