package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.exception.CustomExecutorException;

/**
 * Interface {@link AuthenticationExecutor} is used for user authentication in system.
 *
 * @author MolchanovVladislav
 */
public interface AuthenticationExecutor {
    /**
     * Check user record in system
     * @param email user email
     * @param password user password
     * @return object, which contains information about user
     * @throws CustomExecutorException exception of service level
     */
    UserDataDTO checkUserAccessory(String email, String password) throws CustomExecutorException;
}
