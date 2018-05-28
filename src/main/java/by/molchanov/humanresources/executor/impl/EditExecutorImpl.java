package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.dao.impl.UserDAOImpl;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.dto.VacancyDataDTO;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.JobVacancyStatusType;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.EditExecutor;

import java.util.List;

import static by.molchanov.humanresources.executor.PropertyMessageVariablesName.*;
import static by.molchanov.humanresources.validator.UserDataValidation.isEmailAddressCorrect;
import static by.molchanov.humanresources.validator.UserDataValidation.isUserNameCorrect;
import static by.molchanov.humanresources.validator.VacancyRequestDataValidation.isTextCorrect;
import static by.molchanov.humanresources.validator.VacancyRequestDataValidation.isVacancyNameCorrect;

public class EditExecutorImpl implements EditExecutor {
    private static final EditExecutorImpl EDIT_EXECUTOR = new EditExecutorImpl();

    private JobVacancyDAO jobVacancyDAO = JobVacancyDAOImpl.getInstance();
    private UserDAO userDAO = UserDAOImpl.getInstance();

    private EditExecutorImpl() {

    }

    public static EditExecutorImpl getInstance() {
        return EDIT_EXECUTOR;
    }

    @Override
    public void editVacancy(VacancyDataDTO vacancyDataDTO) throws CustomExecutorException {
        String vacancyName = vacancyDataDTO.getJobVacancy().getName();
        String vacancyRequirement = vacancyDataDTO.getJobVacancy().getRequirement();
        String vacancyId = vacancyDataDTO.getVacancyId();
        String infoMessage = SUCCESSFUL_EDIT;
        if (!isVacancyNameCorrect(vacancyName)) {
            infoMessage = VACANCY_INCORRECT_NAME;
        } else if (!isTextCorrect(vacancyRequirement)) {
            infoMessage = VACANCY_INCORRECT_REQUIREMENT;
        } else {
            JobVacancy jobVacancy;
            int id = Integer.parseInt(vacancyId);
            try {
                jobVacancy = jobVacancyDAO.findById(id);
                jobVacancy.setName(vacancyName);
                jobVacancy.setRequirement(vacancyName);
                jobVacancy.setStatus(JobVacancyStatusType.NEW);
                jobVacancyDAO.update(jobVacancy);
            } catch (CustomDAOException e) {
                throw new CustomExecutorException();
            }
        }
        vacancyDataDTO.setInfoMessage(infoMessage);
    }

    @Override
    public void editUser(UserDataDTO userDataDTO) throws CustomExecutorException {
        User userExemplar = userDataDTO.getUserExemplar();
        Organization organization = userExemplar.getOrganization();
        int userId = userExemplar.getId();
        String newFirstName = userExemplar.getFirstName();
        String newLastName = userExemplar.getLastName();
        String newEmail = userDataDTO.getAltEmail();
        String infoMessage = SUCCESSFUL_EDIT;
        try {
            boolean freeAddress = true;
            if (!userExemplar.getEmail().equals(newEmail)) {
                List<User> users = userDAO.findAll();
                for (User user : users) {
                    if (user.getEmail().equals(newEmail)) {
                        freeAddress = false;
                        break;
                    }
                }
            }
            if (!freeAddress) {
                infoMessage = USER_REGISTRATION_NOT_AVAILABLE_EMAIL_ADDRESS;
            } else if (!isEmailAddressCorrect(newEmail)) {
                infoMessage = USER_REGISTRATION_INCORRECT_EMAIL;
            } else if (!isUserNameCorrect(newFirstName) && isUserNameCorrect(newLastName)) {
                infoMessage = USER_REGISTRATION_INCORRECT_NAME_SURNAME;
            } else {
                userExemplar = userDAO.findById(userId);
                userExemplar.setEmail(newEmail);
                userExemplar.setFirstName(newFirstName);
                userExemplar.setLastName(newLastName);
                userDAO.update(userExemplar);
                userExemplar.setOrganization(organization);
            }
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
        userDataDTO.setInfoMessage(infoMessage);
    }
}
