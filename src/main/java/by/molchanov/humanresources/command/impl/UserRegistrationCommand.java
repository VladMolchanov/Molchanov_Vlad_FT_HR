package by.molchanov.humanresources.command.impl;

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

public class UserRegistrationCommand implements ConcreteCommand {
    private static final UserRegistrationCommand USER_REGISTRATION_COMMAND = new UserRegistrationCommand();
    private static final RegistrationExecutor REGISTRATION_EXECUTOR = RegistrationExecutorImpl.getInstance();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillContentCommand.getInstance();
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
            REGISTRATION_EXECUTOR.userSignUp(userDataDTO);
            FILL_VACANCY_COMMAND.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        if (userDataDTO.getUserExemplar().getRole() != null) {
            requestHolder.addSessionAttribute(USER_INFO, userDataDTO.getUserExemplar());
            requestHolder.addSessionAttribute(ROLE, userDataDTO.getUserExemplar().getRole().getValue());
        }
        requestHolder.addRequestAttribute(INFO_MESSAGE, userDataDTO.getInfoMessage());
    }
}
