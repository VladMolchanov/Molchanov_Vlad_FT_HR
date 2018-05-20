package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dao.OrganizationDAO;
import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.dao.impl.OrganizationDAOImpl;
import by.molchanov.humanresources.dao.impl.UserDAOImpl;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomDAOException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.AuthenticationExecutor;
import by.molchanov.humanresources.security.AESEncryption;

import java.util.List;

import static by.molchanov.humanresources.executor.PropertyMessageVariablesName.UNKNOWN_USER;
import static by.molchanov.humanresources.executor.PropertyMessageVariablesName.USER_AUTHENTICATION_SUCCESSFUL;

public class AuthenticationExecutorImpl implements AuthenticationExecutor {
    private static final AuthenticationExecutorImpl AUTHENTICATION_EXECUTOR = new AuthenticationExecutorImpl();

    private static final UserDAO USER_DAO = UserDAOImpl.getInstance();
    private static final OrganizationDAO ORGANIZATION_DAO = OrganizationDAOImpl.getInstance();

    private static final int PERMISSIBLE_VALUE = 1;
    private static final int FIRST_POSITION = 0;
    private static final int EMPTY_ORG_ID = 0;

    private AuthenticationExecutorImpl() {

    }

    public static AuthenticationExecutorImpl getInstance() {
        return AUTHENTICATION_EXECUTOR;
    }

    @Override
    public UserDataDTO checkUserAccessory(String email, String password) throws CustomExecutorException {
        UserDataDTO userDataDTO = new UserDataDTO();
        String infoMessage = USER_AUTHENTICATION_SUCCESSFUL;
        AESEncryption encryption = new AESEncryption();
        password = encryption.encryptionOfString(password);
        try {
            List<User> users = USER_DAO.findUsersByEmailAndPassword(email, password);
            if (users.size() == PERMISSIBLE_VALUE) {
                User user = users.get(FIRST_POSITION);
                userDataDTO.setUserExemplar(user);
                if (user.getOrganization().getId() != EMPTY_ORG_ID) {
                    Organization organization = ORGANIZATION_DAO.findById(user.getOrganization().getId());
                    userDataDTO.getUserExemplar().setOrganization(organization);
                }
            } else {
                infoMessage = UNKNOWN_USER;
            }
            userDataDTO.setInfoMessage(infoMessage);
        } catch (CustomDAOException e) {
            throw new CustomExecutorException(e);
        }
        return userDataDTO;
    }
}