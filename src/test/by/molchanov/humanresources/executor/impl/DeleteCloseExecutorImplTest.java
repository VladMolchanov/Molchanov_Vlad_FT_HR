package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteCloseExecutor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeleteCloseExecutorImplTest {

    @InjectMocks
    private DeleteCloseExecutor executor = DeleteCloseExecutorImpl.getInstance();

    @Mock
    private JobVacancyDAO jobVacancyDAO;

    @Mock
    private JobRequestDAO jobRequestDAO;

    @Mock
    private UserDAO userDAO;

    private List<String> ids = Arrays.asList("1", "2", "3");
    private List<JobVacancy> vacancies;
    private JobRequest jobRequest = new JobRequest();
    private JobVacancy jobVacancy = new JobVacancy();

    @BeforeTest
    public void before() {
        initMocks(this);
    }

    @AfterMethod
    public void after() {
        reset(jobVacancyDAO, jobRequestDAO, userDAO);
    }

    @Test
    public void deleteVacancyTest() throws CustomDAOException, CustomExecutorException {
        doNothing()
                .when(jobVacancyDAO).delete(any(JobVacancy.class));

        executor.deleteVacancy("1");

        verify(jobVacancyDAO).delete(any(JobVacancy.class));
        verifyNoMoreInteractions(jobVacancyDAO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenDeleteVacancyTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(jobVacancyDAO).delete(any(JobVacancy.class));

        executor.deleteVacancy("1");
    }

    @Test
    public void deleteUserTest() throws CustomDAOException, CustomExecutorException {
        doNothing()
                .when(userDAO).delete(any(User.class));

        executor.deleteUser(ids);

        verify(userDAO, times(3)).delete(any(User.class));
        verifyNoMoreInteractions(userDAO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenDeleteUserTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(userDAO).delete(any(User.class));

        executor.deleteUser(ids);
    }

    @Test
    public void closeOldVacancyTest() throws CustomDAOException, CustomExecutorException {
        vacancies = new LinkedList<>();
        JobVacancy vacancy = new JobVacancy();
        vacancy.setUploadDate("2018-04-20 03:50:05");
        vacancies.add(vacancy);

        when(jobVacancyDAO.findAll())
                .thenReturn(vacancies);
        doNothing()
                .when(jobVacancyDAO).update(any(JobVacancy.class));

        executor.closeOldVacancy();

        verify(jobVacancyDAO).findAll();
        verify(jobVacancyDAO).update(any(JobVacancy.class));
        verifyNoMoreInteractions(jobVacancyDAO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowFirstExceptionWhenCloseOldVacancyTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(jobVacancyDAO).findAll();

        executor.closeOldVacancy();
    }

    @Test
    public void closeRequestTest() throws CustomDAOException, CustomExecutorException {
        when(jobRequestDAO.findById(anyInt()))
                .thenReturn(jobRequest);
        doNothing()
                .when(jobRequestDAO).update(any(JobRequest.class));

        executor.closeRequest("1");

        verify(jobRequestDAO).findById(anyInt());
        verify(jobRequestDAO).update(any(JobRequest.class));
        verifyNoMoreInteractions(jobRequestDAO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenCloseRequestTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(jobRequestDAO).findById(anyInt());

        executor.closeRequest("1");
    }

    @Test
    public void closeVacancyTest() throws CustomDAOException, CustomExecutorException {
        when(jobVacancyDAO.findById(anyInt()))
                .thenReturn(jobVacancy);
        doNothing()
                .when(jobVacancyDAO).update(any(JobVacancy.class));

        executor.closeVacancy("1");

        verify(jobVacancyDAO).findById(anyInt());
        verify(jobVacancyDAO).update(any(JobVacancy.class));
        verifyNoMoreInteractions(jobVacancyDAO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenCloseVacancyTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(jobVacancyDAO).findById(anyInt());

        executor.closeVacancy("1");
    }
}
