package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.exception.CustomExecutorException;

public interface AuthenticationExecutor {
    UserDataDTO checkUserAccessory(String email, String password) throws CustomExecutorException;
}
