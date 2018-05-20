package by.molchanov.humanresources.executor;

import by.molchanov.humanresources.dto.JobRequestDTO;
import by.molchanov.humanresources.dto.OrgDataDTO;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.dto.VacancyDataDTO;
import by.molchanov.humanresources.exception.CustomExecutorException;

public interface RegistrationExecutor {
    void userSignUp(UserDataDTO userDataDTO) throws CustomExecutorException;
    void orgSignUp(OrgDataDTO orgDataDTO) throws CustomExecutorException;
    void vacancySignUp(VacancyDataDTO vacancyDataDTO) throws CustomExecutorException;
    void requestSignUp(JobRequestDTO jobRequestDTO) throws CustomExecutorException;
}
