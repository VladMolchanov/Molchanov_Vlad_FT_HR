package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.exception.CustomExecutorException;

import java.util.List;

/**
 * Interface {@link DeleteCloseExecutor} is used for close or delete records in system.
 *
 * @author MolchanovVladislav
 */
public interface DeleteCloseExecutor {
    /**
     * Delete vacancy from system
     * @param vacancyId id of vacancy
     * @throws CustomExecutorException exception of service level
     */
    void deleteVacancy(String vacancyId) throws CustomExecutorException;
    /**
     * Delete user from system
     * @param usersId id of user
     * @throws CustomExecutorException exception of service level
     */
    void deleteUser(List<String> usersId) throws CustomExecutorException;

    void deleteOrganization(List<String> organizationsId) throws CustomExecutorException;
    /**
     * Change status of old vacancies(more than 15 days) to 'close'
     * @throws CustomExecutorException exception of service level
     */
    void closeOldVacancy() throws CustomExecutorException;
    /**
     * Change status of request to 'rejected'
     * @param requestId id of request
     * @throws CustomExecutorException exception of service level
     */
    void closeRequest(String requestId) throws CustomExecutorException;
    /**
     * Change status of vacancy to 'close'
     * @param vacancyId id of vacancy
     * @throws CustomExecutorException exception of service level
     */
    void closeVacancy(String vacancyId) throws CustomExecutorException;
}
