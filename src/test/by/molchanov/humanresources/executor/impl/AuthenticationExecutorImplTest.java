package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.OrganizationDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.AuthenticationExecutor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class AuthenticationExecutorImplTest {

    @InjectMocks
    private AuthenticationExecutor executor = AuthenticationExecutorImpl.getInstance();

    @Mock
    private UserDAO userDAO;

    @Mock
    private OrganizationDAO organizationDAO;

    private List<User> users;
    private Organization organization;

    @BeforeTest
    public void before() throws NoSuchFieldException, IllegalAccessException {
        initMocks(this);

        users = new LinkedList<>();
        User firstUser = new User();
        firstUser.setId(1);
        firstUser.setFirstName("first");

        organization = new Organization();
        organization.setId(1);
        firstUser.setOrganization(organization);
        users.add(firstUser);
    }

    @AfterTest
    public void after() {
        reset(userDAO, organizationDAO);
    }

    @Test
    public void checkUserAccessoryTest() throws CustomDAOException, CustomExecutorException {
        when(userDAO.findUsersByEmailAndPassword(anyString(), anyString()))
                .thenReturn(users);
        when(organizationDAO.findById(anyInt()))
                .thenReturn(organization);

        UserDataDTO userDataDTO = executor.checkUserAccessory("email", "password");

        verify(userDAO).findUsersByEmailAndPassword(anyString(), anyString());
        verify(organizationDAO).findById(anyInt());
        verifyNoMoreInteractions(userDAO, organizationDAO);

        assertEquals(users.get(0), userDataDTO.getUserExemplar());
        assertEquals(organization, userDataDTO.getUserExemplar().getOrganization());
    }

    @Test(expectedExceptions = CustomExecutorException.class)
    public void shouldThrowExceptionWhenCheckUserAccessoryTest() throws CustomDAOException, CustomExecutorException {
        when(userDAO.findUsersByEmailAndPassword(anyString(), anyString()))
                .thenThrow(new CustomDAOException());

        executor.checkUserAccessory("email", "password");
    }
}
