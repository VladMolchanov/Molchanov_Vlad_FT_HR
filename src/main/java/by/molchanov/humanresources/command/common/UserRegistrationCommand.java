package by.molchanov.humanresources.command.common;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.RegistrationExecutor;
import by.molchanov.humanresources.executor.impl.RegistrationExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;
import static by.molchanov.humanresources.command.SessionRequestAttributeName.FIRST_NAME;
import static by.molchanov.humanresources.command.SessionRequestAttributeName.LAST_NAME;

/**
 * Class {@link UserRegistrationCommand} is used for new user registration.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class UserRegistrationCommand implements ConcreteCommand {
    private static final UserRegistrationCommand USER_REGISTRATION_COMMAND = new UserRegistrationCommand();
    private RegistrationExecutor registrationExecutor = RegistrationExecutorImpl.getInstance();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();
    private static final int FIRST_INDEX = 0;

    private UserRegistrationCommand() {

    }

    public static UserRegistrationCommand getInstance() {
        return USER_REGISTRATION_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String email = requestHolder.getSingleRequestParameter(FIRST_INDEX, EMAIL);
        String password = requestHolder.getSingleRequestParameter(FIRST_INDEX, PASS);
        String repeatPass = requestHolder.getSingleRequestParameter(FIRST_INDEX, REPEAT_PASS);
        String firstName = requestHolder.getSingleRequestParameter(FIRST_INDEX, FIRST_NAME);
        String lastName = requestHolder.getSingleRequestParameter(FIRST_INDEX, LAST_NAME);
        User user = new User();
        user.setEmail(email);
        user.setPass(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        UserDataDTO userDataDTO = new UserDataDTO();
        userDataDTO.setUserExemplar(user);
        userDataDTO.setRepeatPassword(repeatPass);
        try {
            registrationExecutor.userSignUp(userDataDTO);
            fillContentCommand.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        User transferredUser = userDataDTO.getUserExemplar();
        if (transferredUser.getRole() != null) {
            requestHolder.addSessionAttribute(USER_INFO, transferredUser);
            requestHolder.addSessionAttribute(ROLE, transferredUser.getRole().getValue());
        }
        requestHolder.addRequestAttribute(INFO_MESSAGE, userDataDTO.getInfoMessage());
    }
}
