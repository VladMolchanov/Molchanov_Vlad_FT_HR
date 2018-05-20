package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.exception.CustomExecutorException;

import java.util.List;

public interface ConfirmExecutor {
    void confirmVacancy(String vacancyId) throws CustomExecutorException;
    void confirmRequest(String requestId) throws CustomExecutorException;
}
