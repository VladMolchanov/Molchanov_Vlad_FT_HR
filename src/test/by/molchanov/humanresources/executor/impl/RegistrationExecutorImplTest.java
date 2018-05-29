package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.OrganizationDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.dto.JobRequestDataDTO;
import by.molchanov.humanresources.dto.OrgDataDTO;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.dto.VacancyDataDTO;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.RegistrationExecutor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class RegistrationExecutorImplTest {

    @InjectMocks
    private RegistrationExecutor executor = RegistrationExecutorImpl.getInstance();

    @Mock
    private UserDAO userDAO;

    @Mock
    private OrganizationDAO organizationDAO;

    @Mock
    private JobVacancyDAO jobVacancyDAO;

    @Mock
    private JobRequestDAO jobRequestDAO;

    private User user = new User();
    private Organization organization = new Organization();
    private JobVacancy jobVacancy = new JobVacancy();
    private JobRequest jobRequest = new JobRequest();
    private UserDataDTO userDataDTO = new UserDataDTO();
    private OrgDataDTO orgDataDTO = new OrgDataDTO();
    private VacancyDataDTO vacancyDataDTO = new VacancyDataDTO();
    private JobRequestDataDTO jobRequestDataDTO = new JobRequestDataDTO();

    @BeforeTest
    public void before() {
        initMocks(this);
        user.setEmail("email@gmail.com");
        user.setFirstName("first");
        user.setLastName("last");
        user.setPass("pass");
        userDataDTO.setRepeatPassword("pass");
        userDataDTO.setUserExemplar(user);
        userDataDTO.setAltEmail("altemail@gmail.com");
        User orgDirector = new User();
        orgDirector.setOrganization(organization);
        organization.setName("name");
        orgDataDTO.setOrgDirector(orgDirector);
        organization.setWebsite("http://web.com");
        organization.setDescription("desc");
        jobVacancy.setName("name");
        jobVacancy.setRequirement("req");
        vacancyDataDTO.setJobVacancy(jobVacancy);
        jobRequest.setResume("resume");
        jobRequestDataDTO.setJobRequest(jobRequest);
    }

    @AfterMethod
    public void after() {
        reset(userDAO, organizationDAO, jobVacancyDAO, jobRequestDAO);
    }

    @Test
    public void orgSignUpTest() throws CustomDAOException, CustomExecutorException {
        when(organizationDAO.persist(any(Organization.class)))
                .thenReturn(organization);
        doNothing()
                .when(userDAO).updateUserOrgIdRole(user);

        executor.orgSignUp(orgDataDTO);

        verify(organizationDAO).persist(any(Organization.class));
        verify(userDAO).updateUserOrgIdRole(any(User.class));
        verifyNoMoreInteractions(userDAO, organizationDAO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenOrgSignUpTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(organizationDAO).persist(any(Organization.class));

        executor.orgSignUp(orgDataDTO);
    }

    @Test
    public void vacancySignUpTest() throws CustomDAOException, CustomExecutorException {
        when(jobVacancyDAO.persist(any(JobVacancy.class)))
                .thenReturn(jobVacancy);

        executor.vacancySignUp(vacancyDataDTO);

        verify(jobVacancyDAO).persist(any(JobVacancy.class));
        verifyNoMoreInteractions(jobVacancyDAO);
    }

    @Test
    public void userSignUpTest() throws CustomDAOException, CustomExecutorException {
        when(userDAO.persist(any(User.class)))
                .thenReturn(user);

        executor.userSignUp(userDataDTO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenUserSignUpTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(userDAO).persist(any(User.class));

        executor.userSignUp(userDataDTO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenVacancySignUpTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(jobVacancyDAO).persist(any(JobVacancy.class));

        executor.vacancySignUp(vacancyDataDTO);
    }

    @Test
    public void requestSignUpTest() throws CustomDAOException, CustomExecutorException {
        when(jobRequestDAO.persist(any(JobRequest.class)))
                .thenReturn(jobRequest);

        executor.requestSignUp(jobRequestDataDTO);

        verify(jobRequestDAO).persist(any(JobRequest.class));
        verifyNoMoreInteractions(jobRequestDAO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenRequestSignUpTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(jobRequestDAO).persist(any(JobRequest.class));

        executor.requestSignUp(jobRequestDataDTO);
    }
}
