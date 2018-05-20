package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.dto.JobRequestDataDTO;
import by.molchanov.humanresources.dto.OrgDataDTO;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.dto.VacancyDataDTO;
import by.molchanov.humanresources.exception.CustomExecutorException;

/**
 * Interface {@link RegistrationExecutor} is used for different registrations (sign up).
 *
 * @author MolchanovVladislav
 */
public interface RegistrationExecutor {
    /**
     * Sign up of new user
     * @param userDataDTO object with registration data about user
     * @throws CustomExecutorException exception of service level
     */
    void userSignUp(UserDataDTO userDataDTO) throws CustomExecutorException;

    /**
     * Sign up of new organization
     * @param orgDataDTO object with registration data about organization
     * @throws CustomExecutorException exception of service level
     */
    void orgSignUp(OrgDataDTO orgDataDTO) throws CustomExecutorException;

    /**
     * Sign up of new vacancy
     * @param vacancyDataDTO object with registration data about vacancy
     * @throws CustomExecutorException exception of service level
     */
    void vacancySignUp(VacancyDataDTO vacancyDataDTO) throws CustomExecutorException;

    /**
     * Sign up of new request
     * @param jobRequestDataDTO object with registration data about request
     * @throws CustomExecutorException exception of service level
     */
    void requestSignUp(JobRequestDataDTO jobRequestDataDTO) throws CustomExecutorException;
}
