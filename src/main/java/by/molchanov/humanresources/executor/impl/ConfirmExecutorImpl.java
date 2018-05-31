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
import by.molchanov.humanresources.executor.ConfirmExecutor;

import java.util.List;

/**
 * Class {@link ConfirmExecutorImpl} is used for confirm records.
 *
 * @author Molchanov Vladislav
 * @see ConfirmExecutor
 */
public class ConfirmExecutorImpl implements ConfirmExecutor {
    private static final ConfirmExecutorImpl CONFIRM_EXECUTOR = new ConfirmExecutorImpl();

    private JobVacancyDAO jobVacancyDAO = JobVacancyDAOImpl.getInstance();
    private JobRequestDAO jobRequestDAO = JobRequestDAOImpl.getInstance();
    private UserDAO userDAO = UserDAOImpl.getInstance();
    private OrganizationDAO organizationDAO = OrganizationDAOImpl.getInstance();

    public static ConfirmExecutorImpl getInstance() {
        return CONFIRM_EXECUTOR;
    }

    private ConfirmExecutorImpl() {

    }

    @Override
    public void confirmVacancy(String vacancyId) throws CustomExecutorException {
        int id = Integer.parseInt(vacancyId);
        try {
            JobVacancy jobVacancy = jobVacancyDAO.findById(id);
            jobVacancy.setStatus(JobVacancyStatusType.OPEN);
            jobVacancyDAO.update(jobVacancy);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }

    @Override
    public void approveRequest(String requestId) throws CustomExecutorException {
        JobRequest jobRequest;
        int id = Integer.parseInt(requestId);
        try {
            jobRequest = jobRequestDAO.findById(id);
            jobRequest.setStatus(JobRequestStatusType.APPROVED);
            jobRequestDAO.update(jobRequest);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }

    @Override
    public void riseToAdmin(List<String> usersId) throws CustomExecutorException {
        User user;
        int id;
        for (String userId: usersId) {
            id = Integer.parseInt(userId);
            try {
                user = userDAO.findById(id);
                user.setRole(UserStatusType.ADMIN);
                userDAO.update(user);
                organizationDAO.delete(user.getOrganization());
            } catch (CustomDAOException e) {
                throw new CustomExecutorException(e);
            }
        }
    }

}
