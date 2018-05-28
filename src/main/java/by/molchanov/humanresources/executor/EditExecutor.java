package by.molchanov.humanresources.executor;


import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.dto.VacancyDataDTO;
import by.molchanov.humanresources.exception.CustomExecutorException;

public interface EditExecutor {
    void editVacancy(VacancyDataDTO vacancyDataDTO) throws CustomExecutorException;
    void editUser(UserDataDTO userDataDTO) throws CustomExecutorException;
}
