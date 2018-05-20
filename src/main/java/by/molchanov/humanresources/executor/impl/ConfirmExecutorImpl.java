package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.impl.JobRequestDAOImpl;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobRequestStatusType;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.JobVacancyStatusType;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.ConfirmExecutor;

import java.util.List;

public class ConfirmExecutorImpl implements ConfirmExecutor {
    private static final ConfirmExecutorImpl CONFIRM_EXECUTOR = new ConfirmExecutorImpl();

    private static final JobVacancyDAO JOB_VACANCY_DAO = JobVacancyDAOImpl.getInstance();
    private static final JobRequestDAO JOB_REQUEST_DAO = JobRequestDAOImpl.getInstance();

    public static ConfirmExecutorImpl getInstance() {
        return CONFIRM_EXECUTOR;
    }

    @Override
    public void confirmVacancy(String vacancyId) throws CustomExecutorException {
        int id = Integer.parseInt(vacancyId);
        try {
            JobVacancy jobVacancy = JOB_VACANCY_DAO.findById(id);
            jobVacancy.setStatus(JobVacancyStatusType.OPEN);
            JOB_VACANCY_DAO.update(jobVacancy);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }

    @Override
    public void confirmRequest(String requestId) throws CustomExecutorException {
        int id = Integer.parseInt(requestId);
        try {
            JobRequest jobRequest = JOB_REQUEST_DAO.findById(id);
            jobRequest.setStatus(JobRequestStatusType.ADDED);
            JOB_REQUEST_DAO.update(jobRequest);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }
}
