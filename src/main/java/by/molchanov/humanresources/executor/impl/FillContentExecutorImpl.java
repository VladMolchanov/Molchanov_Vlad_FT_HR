package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.OrganizationDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.dao.impl.JobRequestDAOImpl;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.dao.impl.OrganizationDAOImpl;
import by.molchanov.humanresources.dao.impl.UserDAOImpl;
import by.molchanov.humanresources.entity.*;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillContentExecutor;

import java.util.ArrayList;
import java.util.List;

import static by.molchanov.humanresources.entity.JobVacancyStatusType.OPEN;
import static by.molchanov.humanresources.entity.JobVacancyStatusType.NEW;

/**
 * Class {@link FillContentExecutorImpl} used for fill page content.
 *
 * @author MolcanovVladislav
 * @see FillContentExecutor
 */
public class FillContentExecutorImpl implements FillContentExecutor {
    private static final FillContentExecutorImpl FILL_VACANCY_EXECUTOR = new FillContentExecutorImpl();
    private static final String ROLE_ADMIN = "admin";
    private static final String ROLE_DIRECTOR = "director";

    private JobVacancyDAO jobVacancyDAO = JobVacancyDAOImpl.getInstance();
    private JobRequestDAO jobRequestDAO = JobRequestDAOImpl.getInstance();
    private UserDAO userDAO = UserDAOImpl.getInstance();
    private OrganizationDAO organizationDAO = OrganizationDAOImpl.getInstance();

    private FillContentExecutorImpl() {

    }

    public static FillContentExecutorImpl getInstance() {
        return FILL_VACANCY_EXECUTOR;
    }

    @Override
    public List<JobVacancy> fillVacancy(String userRole, String searchField, int startVacancyNumber,
                                        int vacanciesQuantity) throws CustomExecutorException {
        List<JobVacancy> vacancies;
        String emptySearchField = "";
        JobVacancyStatusType jobVacancyStatusType = OPEN;
        if (ROLE_ADMIN.equals(userRole)) {
            jobVacancyStatusType = NEW;
        }
        if (searchField == null) {
            searchField = emptySearchField;
        }
        try {
            vacancies = jobVacancyDAO.findVacancyInfoByType(jobVacancyStatusType, searchField, startVacancyNumber, vacanciesQuantity);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
        return vacancies;
    }

    @Override
    public List<JobRequest> fillRequest(String userRole, int organizationId, String searchField,
                                        int startRequestNumber, int requestsQuantity) throws CustomExecutorException {
        List<JobRequest> requests;
        JobRequestStatusType jobRequestStatusType;
        String emptySearchField = "";
        if (searchField == null) {
            searchField = emptySearchField;
        }
        if (ROLE_DIRECTOR.equals(userRole)) {
            jobRequestStatusType = JobRequestStatusType.REJECTED;
            try {
                requests = jobRequestDAO.findRequestByTypeRole(jobRequestStatusType, organizationId, searchField, startRequestNumber, requestsQuantity);
            } catch (CustomDAOException e) {
                throw new CustomExecutorException(e);
            }
        } else {
            requests = new ArrayList<>();
        }
        return requests;
    }

    @Override
    public List<User> fillUser(String userRole, int startUserNumber, int usersQuantity) throws CustomExecutorException {
        List<User> users;
        if (ROLE_ADMIN.equals(userRole)) {
            try {
                users = userDAO.findPartOfUsers(UserStatusType.ADMIN, startUserNumber, usersQuantity);
            } catch (CustomDAOException e) {
                throw new CustomExecutorException(e);
            }
        } else {
            users = new ArrayList<>();
        }
        return users;
    }

    @Override
    public List<Organization> fillOrganization(int startOrganizationNumber, int organizationsQuantity) throws CustomExecutorException {
        List<Organization> organizations;
        try {
            organizations = organizationDAO.findPartOfOrganizations(startOrganizationNumber, organizationsQuantity);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
        return organizations;
    }

    @Override
    public int findVacanciesCount(String userRole, String searchField) throws CustomExecutorException {
        int count;
        String emptySearchField = "";
        JobVacancyStatusType jobVacancyStatusType = JobVacancyStatusType.OPEN;
        if (ROLE_ADMIN.equals(userRole)) {
            jobVacancyStatusType = JobVacancyStatusType.NEW;
        }
        if (searchField == null) {
            searchField = emptySearchField;
        }
        try {
            count = jobVacancyDAO.findVacanciesCount(jobVacancyStatusType, searchField);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
        return count;
    }

    @Override
    public int findRequestsCount(String userRole, int orgId, String searchField) throws CustomExecutorException {
        int count = 0;
        String emptySearchField = "";
        JobRequestStatusType jobRequestStatusType;
        if (searchField == null) {
            searchField = emptySearchField;
        }
        if (ROLE_DIRECTOR.equals(userRole)) {
            try {
                jobRequestStatusType = JobRequestStatusType.REJECTED;
                count = jobRequestDAO.findRequestsCount(jobRequestStatusType, searchField, orgId);
            } catch (CustomDAOException e) {
                throw new CustomExecutorException(e);
            }
        }
        return count;
    }

    @Override
    public int findUsersCount(String userRole) throws CustomExecutorException {
        int count = 0;
        if (ROLE_ADMIN.equals(userRole)) {
            try {
                count = userDAO.findUsersCount(UserStatusType.ADMIN);
            } catch (CustomDAOException e) {
                throw new CustomExecutorException(e);
            }
        }
        return count;
    }

    @Override
    public int findOrganizationsCount() throws CustomExecutorException {
        int count;
        try {
            count = organizationDAO.findOrganizationsCount();
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
        return count;
    }
}
