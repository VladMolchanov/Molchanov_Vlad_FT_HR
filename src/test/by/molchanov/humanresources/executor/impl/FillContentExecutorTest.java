package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.OrganizationDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.entity.*;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillContentExecutor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class FillContentExecutorTest {

    @InjectMocks
    private FillContentExecutor executor = FillContentExecutorImpl.getInstance();

    @Mock
    private JobVacancyDAO jobVacancyDAO;

    @Mock
    private JobRequestDAO jobRequestDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private OrganizationDAO organizationDAO;

    private List<JobVacancy> vacancies = new ArrayList<>();
    private List<JobRequest> requests = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Organization> organizations = new ArrayList<>();

    @BeforeTest
    public void before() {
        initMocks(this);
    }

    @AfterTest
    public void after() {
        reset(jobVacancyDAO, jobRequestDAO, userDAO, organizationDAO);
    }

    @Test
    public void fillVacancyTest() throws CustomDAOException, CustomExecutorException {
        when(jobVacancyDAO.findVacancyInfoByType(any(JobVacancyStatusType.class), anyString(), anyInt(), anyInt()))
                .thenReturn(vacancies);

        List<JobVacancy> returnedVacancies = executor.fillVacancy("role", "field", 1, 1);

        verify(jobVacancyDAO).findVacancyInfoByType(any(JobVacancyStatusType.class), anyString(), anyInt(), anyInt());
        verifyNoMoreInteractions(jobVacancyDAO);

        assertEquals(returnedVacancies, vacancies);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenFillVacancyTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(jobVacancyDAO).findVacancyInfoByType(any(JobVacancyStatusType.class), anyString(), anyInt(), anyInt());

        executor.fillVacancy("role", "field", 1, 1);
    }

    @Test
    public void fillRequestTest() throws CustomDAOException, CustomExecutorException {
        when(jobRequestDAO.findRequestByTypeRole(any(JobRequestStatusType.class), anyInt(), anyString(), anyInt(), anyInt()))
                .thenReturn(requests);

        List<JobRequest> returnedRequests = executor.fillRequest("director", 1, "", 1, 1);

        verify(jobRequestDAO).findRequestByTypeRole(any(JobRequestStatusType.class), anyInt(), anyString(), anyInt(), anyInt());
        verifyNoMoreInteractions(jobRequestDAO);

        assertEquals(returnedRequests, requests);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenFillRequestTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(jobRequestDAO).findRequestByTypeRole(any(JobRequestStatusType.class), anyInt(), anyString(), anyInt(), anyInt());

        executor.fillRequest("director", 1, "", 1, 1);
    }

    @Test
    public void fillUserTest() throws CustomDAOException, CustomExecutorException {
        when(userDAO.findPartOfUsers(any(UserStatusType.class), anyInt(), anyInt()))
                .thenReturn(users);

        List<User> returnedUsers = executor.fillUser("admin", 1, 1);

        verify(userDAO).findPartOfUsers(any(UserStatusType.class), anyInt(), anyInt());
        verifyNoMoreInteractions(userDAO);

        assertEquals(returnedUsers, users);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenFillUserTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(userDAO).findPartOfUsers(any(UserStatusType.class), anyInt(), anyInt());

        executor.fillUser("admin", 1, 1);
    }

    @Test
    public void fillOrganizationTest() throws CustomDAOException, CustomExecutorException {
        when(organizationDAO.findPartOfOrganizations(anyInt(), anyInt()))
                .thenReturn(organizations);

        List<Organization> returnedOrganizations = executor.fillOrganization(1, 1);

        verify(organizationDAO).findPartOfOrganizations(anyInt(), anyInt());
        verifyNoMoreInteractions(organizationDAO);

        assertEquals(returnedOrganizations, organizations);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenFillOrganizationTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(organizationDAO).findPartOfOrganizations(anyInt(), anyInt());

        executor.fillOrganization(1, 1);
    }

    @Test
    public void findVacanciesCountTest() throws CustomDAOException, CustomExecutorException {
        when(jobVacancyDAO.findVacanciesCount(any(JobVacancyStatusType.class), anyString()))
                .thenReturn(1);

        int count = executor.findVacanciesCount("role", "field");

        verify(jobVacancyDAO).findVacanciesCount(any(JobVacancyStatusType.class), anyString());
        verifyNoMoreInteractions(jobVacancyDAO);

        assertEquals(count, 1);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenFindVacanciesCountTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(jobVacancyDAO).findVacanciesCount(any(JobVacancyStatusType.class), anyString());

        executor.findVacanciesCount("role", "field");
    }

    @Test
    public void findRequestsCountTest() throws CustomDAOException, CustomExecutorException {
        when(jobRequestDAO.findRequestsCount(any(JobRequestStatusType.class), anyString(), anyInt()))
                .thenReturn(1);

        int count = executor.findRequestsCount("director", 1, "field");

        verify(jobRequestDAO).findRequestsCount(any(JobRequestStatusType.class), anyString(), anyInt());
        verifyNoMoreInteractions(jobRequestDAO);

        assertEquals(count, 1);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenFindRequestsCountTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(jobRequestDAO).findRequestsCount(any(JobRequestStatusType.class), anyString(), anyInt());

        executor.findRequestsCount("director", 1, "field");
    }

    @Test
    public void findUsersCountTest() throws CustomDAOException, CustomExecutorException {
        when(userDAO.findUsersCount(any(UserStatusType.class)))
                .thenReturn(1);

        int count = executor.findUsersCount("admin");

        verify(userDAO).findUsersCount(any(UserStatusType.class));
        verifyNoMoreInteractions(userDAO);

        assertEquals(count, 1);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenFindUsersCountTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(userDAO).findUsersCount(any(UserStatusType.class));

        executor.findUsersCount("admin");
    }

    @Test
    public void findOrganizationsCountTest() throws CustomDAOException, CustomExecutorException {
        when(organizationDAO.findOrganizationsCount())
                .thenReturn(1);

        int count = executor.findOrganizationsCount();

        verify(organizationDAO).findOrganizationsCount();
        verifyNoMoreInteractions(organizationDAO);

        assertEquals(count, 1);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenFindOrganizationsCountTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(organizationDAO).findOrganizationsCount();

        executor.findOrganizationsCount();
    }
}
