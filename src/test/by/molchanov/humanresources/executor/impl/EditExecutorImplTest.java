package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.dto.VacancyDataDTO;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.EditExecutor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class EditExecutorImplTest {

    @InjectMocks
    private EditExecutor executor = EditExecutorImpl.getInstance();

    @Mock
    private JobVacancyDAO jobVacancyDAO;

    @Mock
    private UserDAO userDAO;

    private VacancyDataDTO vacancyDataDTO = new VacancyDataDTO();
    private JobVacancy jobVacancy = new JobVacancy();
    private User user = new User();
    private UserDataDTO userDataDTO = new UserDataDTO();

    @BeforeTest
    public void before() {
        initMocks(this);
        vacancyDataDTO.setJobVacancy(jobVacancy);
        vacancyDataDTO.setVacancyId("1");
        jobVacancy.setName("name");
        jobVacancy.setRequirement("req");
        userDataDTO.setUserExemplar(user);
        userDataDTO.setAltEmail("alt@gmail.com");
        user.setId(1);
        user.setEmail("email@gmail.com");
        user.setFirstName("first");
        user.setLastName("last");
    }

    @AfterTest
    public void after() {
        reset(jobVacancyDAO, userDAO);
    }

    @Test
    public void editVacancyTest() throws CustomDAOException, CustomExecutorException {
        when(jobVacancyDAO.findById(anyInt()))
                .thenReturn(jobVacancy);
        doNothing()
                .when(jobVacancyDAO).update(any(JobVacancy.class));

        executor.editVacancy(vacancyDataDTO);

        verify(jobVacancyDAO).findById(anyInt());
        verify(jobVacancyDAO).update(any(JobVacancy.class));
        verifyNoMoreInteractions(jobVacancyDAO);
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenEditVacancyTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(jobVacancyDAO).findById(anyInt());

        executor.editVacancy(vacancyDataDTO);
    }

    @Test
    public void editUserTest() throws CustomDAOException, CustomExecutorException {
        when(userDAO.findById(anyInt()))
                .thenReturn(user);
        doNothing()
                .when(userDAO).update(any(User.class));

        executor.editUser(userDataDTO);

        verify(userDAO).findById(anyInt());
        verify(userDAO).update(any(User.class));
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenEditUserTest() throws CustomDAOException, CustomExecutorException {
        doThrow(new CustomDAOException())
                .when(userDAO).findById(anyInt());

        executor.editUser(userDataDTO);
    }
}
