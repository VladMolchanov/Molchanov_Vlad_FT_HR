package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.dao.impl.JobRequestDAOImpl;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.dao.impl.UserDAOImpl;
import by.molchanov.humanresources.entity.*;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteCloseExecutor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class {@link DeleteCloseExecutorImpl} used for delete or close records.
 *
 * @author MolcanovVladislav
 * @see DeleteCloseExecutor
 */
public class DeleteCloseExecutorImpl implements DeleteCloseExecutor {
    private static final DeleteCloseExecutorImpl DELETE_EXECUTOR = new DeleteCloseExecutorImpl();

    private static final JobVacancyDAO JOB_VACANCY_DAO = JobVacancyDAOImpl.getInstance();
    private static final JobRequestDAO JOB_REQUEST_DAO = JobRequestDAOImpl.getInstance();
    private static final UserDAO USER_DAO = UserDAOImpl.getInstance();

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final int MAX_AVAILABLE_DURATION = 15;

    private DeleteCloseExecutorImpl() {}

    public static DeleteCloseExecutorImpl getInstance() {
        return DELETE_EXECUTOR;
    }

    @Override
    public void deleteVacancy(String vacancyId) throws CustomExecutorException {
        JobVacancy jobVacancy = new JobVacancy();
        int id = Integer.parseInt(vacancyId);
        jobVacancy.setId(id);
        try {
            JOB_VACANCY_DAO.delete(jobVacancy);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }

    @Override
    public void deleteRequest(String requestId) throws CustomExecutorException {
        JobRequest jobRequest = new JobRequest();
        int id = Integer.parseInt(requestId);
        jobRequest.setId(id);
        try {
            JOB_REQUEST_DAO.delete(jobRequest);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }

    @Override
    public void deleteUser(String userId) throws CustomExecutorException {
        User user = new User();
        int id = Integer.parseInt(userId);
        user.setId(id);
        try {
            USER_DAO.delete(user);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }

    @Override
    public void closeOldVacancy() throws CustomExecutorException {
        List<JobVacancy> vacancies;
        JobVacancy bufJobVacancy;
        try {
            vacancies = JOB_VACANCY_DAO.findAll();
            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            Date currentDate = Calendar.getInstance().getTime();
            Date vacancyUploadDate;
            long difference;
            int days;
            for (JobVacancy vacancy: vacancies) {
                String uploadDate = vacancy.getUploadDate();
                vacancyUploadDate = format.parse(uploadDate);
                difference = currentDate.getTime() - vacancyUploadDate.getTime();
                days =  (int)(difference / (24 * 60 * 60 * 1000));
                if (days > MAX_AVAILABLE_DURATION) {
                    bufJobVacancy = vacancy;
                    vacancy.setStatus(JobVacancyStatusType.CLOSE);
                    JOB_VACANCY_DAO.update(bufJobVacancy);
                }
            }
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        } catch (ParseException e) {
            throw new CustomExecutorException("Date parse error!", e);
        }
    }

    @Override
    public void closeRequest(String requestId) throws CustomExecutorException {
        JobRequest jobRequest;
        int id = Integer.parseInt(requestId);
        try {
            jobRequest = JOB_REQUEST_DAO.findById(id);
            jobRequest.setStatus(JobRequestStatusType.REJECTED);
            JOB_REQUEST_DAO.update(jobRequest);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }

    @Override
    public void closeVacancy(String vacancyId) throws CustomExecutorException {
        JobVacancy jobVacancy;
        int id = Integer.parseInt(vacancyId);
        try {
            jobVacancy = JOB_VACANCY_DAO.findById(id);
            jobVacancy.setStatus(JobVacancyStatusType.CLOSE);
            JOB_VACANCY_DAO.update(jobVacancy);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
    }
}
