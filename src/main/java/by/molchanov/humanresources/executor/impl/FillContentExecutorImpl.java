package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.impl.JobRequestDAOImpl;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.JobVacancyStatusType;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillContentExecutor;

import java.util.List;

import static by.molchanov.humanresources.entity.JobVacancyStatusType.OPEN;
import static by.molchanov.humanresources.entity.JobVacancyStatusType.NEW;

public class FillContentExecutorImpl implements FillContentExecutor {
    private static final FillContentExecutorImpl FILL_VACANCY_EXECUTOR = new FillContentExecutorImpl();
    private static final String ROLE_ADMIN = "admin";

    private static final JobVacancyDAO JOB_VACANCY_DAO = JobVacancyDAOImpl.getInstance();
    private static final JobRequestDAO JOB_REQUEST_DAO = JobRequestDAOImpl.getInstance();

    private FillContentExecutorImpl() {

    }

    public static FillContentExecutorImpl getInstance() {
        return FILL_VACANCY_EXECUTOR;
    }

    @Override
    public List<JobVacancy> fillVacancy(String userRole) throws CustomExecutorException {
        List<JobVacancy> vacancies;
        JobVacancyStatusType jobVacancyStatusType = OPEN;
        if (ROLE_ADMIN.equals(userRole)) {
            jobVacancyStatusType = NEW;
        }
        try {
            vacancies = JOB_VACANCY_DAO.findVacancyInfoByType(jobVacancyStatusType);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
        return vacancies;
    }

    @Override
    public List<JobRequest> fillRequest(String userRole, int organizationId) throws CustomExecutorException {
        List<JobRequest> requests;
        try {
            requests = JOB_REQUEST_DAO.findRequestByTypeRole(userRole, organizationId);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
        return requests;
    }
}
