package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.exception.CustomExecutorException;

import java.util.List;

/**
 * Interface {@link ConfirmExecutor} is used for confirm records.
 *
 * @author MolchanovVladislav
 */
public interface ConfirmExecutor {
    /**
     * Change status of vacancy to 'open'
     * @param vacancyId id of vacancy
     * @throws CustomExecutorException exception of service level
     */
    void confirmVacancy(String vacancyId) throws CustomExecutorException;

    void approveRequest(String requestId) throws CustomExecutorException;

    void riseToAdmin(List<String> usersId) throws CustomExecutorException;
}
