package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.ConfirmExecutor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConfirmExecutorImplTest {

    @InjectMocks
    private ConfirmExecutor executor = ConfirmExecutorImpl.getInstance();

    @Mock
    private JobVacancyDAO jobVacancyDAO;

    @Mock
    private UserDAO userDAO;

    private JobVacancy jobVacancy = new JobVacancy();
    private User user = new User();
    private List<String> ids = Arrays.asList("1", "2", "3");

    @BeforeTest
    public void before() {
        initMocks(this);
    }

    @AfterTest
    public void after() {
        reset(jobVacancyDAO);
    }

    @Test
    public void confirmVacancyTest() throws CustomDAOException, CustomExecutorException {
        when(jobVacancyDAO.findById(anyInt()))
                .thenReturn(jobVacancy);
        doNothing()
                .when(jobVacancyDAO).update(any(JobVacancy.class));

        executor.confirmVacancy("1");

        verify(jobVacancyDAO).findById(anyInt());
        verify(jobVacancyDAO).update(any(JobVacancy.class));
        verifyNoMoreInteractions(jobVacancyDAO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenConfirmVacancyTest() throws CustomDAOException, CustomExecutorException {
        when(jobVacancyDAO.findById(anyInt()))
                .thenThrow(new CustomDAOException());

        executor.confirmVacancy("1");
    }

    @Test
    public void riseToAdminTest() throws CustomDAOException, CustomExecutorException {
        when(userDAO.findById(anyInt()))
                .thenReturn(user);
        doNothing()
                .when(userDAO).update(any(User.class));

        executor.riseToAdmin(ids);

        verify(userDAO, times(3)).findById(anyInt());
        verify(userDAO, times(3)).update(any(User.class));
        verifyNoMoreInteractions(userDAO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenRiseToAdminTest() throws CustomDAOException, CustomExecutorException {
        when(userDAO.findById(anyInt()))
                .thenThrow(new CustomDAOException());

        executor.riseToAdmin(ids);
    }
}
