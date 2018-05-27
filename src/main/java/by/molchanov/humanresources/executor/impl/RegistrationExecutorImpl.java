package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.OrganizationDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.dao.impl.JobRequestDAOImpl;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.dao.impl.OrganizationDAOImpl;
import by.molchanov.humanresources.dao.impl.UserDAOImpl;
import by.molchanov.humanresources.dto.JobRequestDataDTO;
import by.molchanov.humanresources.dto.OrgDataDTO;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.dto.VacancyDataDTO;
import by.molchanov.humanresources.entity.*;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.RegistrationExecutor;
import by.molchanov.humanresources.security.AESEncryption;

import java.util.List;

import static by.molchanov.humanresources.executor.PropertyMessageVariablesName.*;
import static by.molchanov.humanresources.validator.UserDataValidation.*;
import static by.molchanov.humanresources.validator.OrganizationDataValidation.*;
import static by.molchanov.humanresources.validator.VacancyRequestDataValidation.*;

/**
 * Class {@link RegistrationExecutorImpl} used for different registration.
 *
 * @author MolcanovVladislav
 * @see RegistrationExecutor
 */
public class RegistrationExecutorImpl implements RegistrationExecutor {
    private static final RegistrationExecutorImpl REGISTRATION_EXECUTOR = new RegistrationExecutorImpl();

    private static final UserDAO USER_DAO = UserDAOImpl.getInstance();
    private static final OrganizationDAO ORGANIZATION_DAO = OrganizationDAOImpl.getInstance();
    private static final JobVacancyDAO JOB_VACANCY_DAO = JobVacancyDAOImpl.getInstance();
    private static final JobRequestDAO JOB_REQUEST_DAO = JobRequestDAOImpl.getInstance();

    private RegistrationExecutorImpl() {

    }

    public static RegistrationExecutorImpl getInstance() {
        return REGISTRATION_EXECUTOR;
    }

    @Override
    public void userSignUp(UserDataDTO userDataDTO) throws CustomExecutorException {
        AESEncryption encryption = new AESEncryption();
        String email = userDataDTO.getUserExemplar().getEmail();
        String password = userDataDTO.getUserExemplar().getPass();
        password = encryption.encryptionOfString(password);
        String repeatPass = userDataDTO.getRepeatPassword();
        repeatPass = encryption.encryptionOfString(repeatPass);
        String firstName = userDataDTO.getUserExemplar().getFirstName();
        String lastName = userDataDTO.getUserExemplar().getLastName();
        String infoMessage = USER_SUCCESSFUL_REGISTRATION;
        try {
            List<User> users = USER_DAO.findAll();
            boolean freeAddress = true;
            for (User user : users) {
                String userEmail = user.getEmail();
                if (email.equals(userEmail)) {
                    freeAddress = false;
                    break;
                }
            }
            if (!isEmailAddressCorrect(email)) {
                infoMessage = USER_REGISTRATION_INCORRECT_EMAIL;
            } else if (!isUserNameCorrect(firstName) && isUserNameCorrect(lastName)) {
                infoMessage = USER_REGISTRATION_INCORRECT_NAME_SURNAME;
            } else if (!isUserPasswordCorrect(password)) {
                infoMessage = USER_REGISTRATION_INCORRECT_PASS;
            } else if (!freeAddress) {
                infoMessage = USER_REGISTRATION_NOT_AVAILABLE_EMAIL_ADDRESS;
            } else if (!password.equals(repeatPass)) {
                infoMessage = USER_REGISTRATION_NOT_SAME_PASS;
            } else {
                UserStatusType userStatusType = UserStatusType.ASPIRANT;
                User user = userDataDTO.getUserExemplar();
                user.setRole(userStatusType);
                user.setPass(password);
                user = USER_DAO.persist(user);
                user.setRole(userStatusType);
                userDataDTO.setUserExemplar(user);
            }
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
        userDataDTO.setInfoMessage(infoMessage);
    }

    @Override
    public void orgSignUp(OrgDataDTO orgDataDTO) throws CustomExecutorException {
        String name = orgDataDTO.getOrgDirector().getOrganization().getName();
        String website = orgDataDTO.getOrgDirector().getOrganization().getWebsite();
        String description = orgDataDTO.getOrgDirector().getOrganization().getDescription();
        OrganizationType organizationType = orgDataDTO.getOrgDirector().getOrganization().getType();
        User user = orgDataDTO.getOrgDirector();
        String infoMessage = USER_SUCCESSFUL_REGISTRATION;
        if (!isOrgNameCorrect(name)) {
            infoMessage = ORG_REGISTRATION_INCORRECT_NAME;
        } else if (!isWEBSiteCorrect(website)) {
            infoMessage = ORG_REGISTRATION_INCORRECT_WEBSITE_URL;
        } else if (!isDescriptionCorrect(description)) {
            infoMessage = ORG_REGISTRATION_INCORRECT_DESCRIPTION;
        } else {
            Organization organization = new Organization(name, website, description, organizationType);
            try {
                organization = ORGANIZATION_DAO.persist(organization);
                user.setOrganization(organization);
                UserStatusType userType = UserStatusType.DIRECTOR;
                user.setRole(userType);
                USER_DAO.updateUserOrgIdRole(user);
            } catch (CustomDAOException e) {
                throw new CustomExecutorException(e);
            }
        }
        orgDataDTO.setInfoMessage(infoMessage);
    }

    @Override
    public void vacancySignUp(VacancyDataDTO vacancyDataDTO) throws CustomExecutorException {
        String vacancyName = vacancyDataDTO.getJobVacancy().getName();
        String vacancyRequirement = vacancyDataDTO.getJobVacancy().getRequirement();
        Organization organization = vacancyDataDTO.getJobVacancy().getOrganization();
        String infoMessage = VACANCY_SUCCESSFUL_REGISTRATION;
        if (!isVacancyNameCorrect(vacancyName)) {
            infoMessage = VACANCY_INCORRECT_NAME;
        } else if (!isTextCorrect(vacancyRequirement)) {
            infoMessage = VACANCY_INCORRECT_REQUIREMENT;
        } else {
            JobVacancyStatusType statusType = JobVacancyStatusType.NEW;
            JobVacancy jobVacancy = new JobVacancy(organization, vacancyName, vacancyRequirement, statusType);
            try {
                JOB_VACANCY_DAO.persist(jobVacancy);
            } catch (CustomDAOException e) {
                throw new CustomExecutorException();
            }
        }
        vacancyDataDTO.setInfoMessage(infoMessage);
    }

    @Override
    public void requestSignUp(JobRequestDataDTO jobRequestDataDTO) throws CustomExecutorException {
        String resume = jobRequestDataDTO.getJobRequest().getResume();
        JobRequest jobRequest = jobRequestDataDTO.getJobRequest();
        String infoMessage = USER_SUCCESSFUL_REGISTRATION;
        if (!isTextCorrect(resume)) {
            infoMessage = REQUEST_INCORRECT_RESUME;
        } else {
            JobRequestStatusType status = JobRequestStatusType.ADDED;
            jobRequest.setStatus(status);
            try {
                JOB_REQUEST_DAO.persist(jobRequest);
            } catch (CustomDAOException e) {
                throw new CustomExecutorException(e);
            }
        }
        jobRequestDataDTO.setInfoMessage(infoMessage);
    }
}
