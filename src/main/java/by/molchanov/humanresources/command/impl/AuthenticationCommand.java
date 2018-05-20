package by.molchanov.humanresources.command.impl;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.AuthenticationExecutor;
import by.molchanov.humanresources.executor.impl.AuthenticationExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;

public class AuthenticationCommand implements ConcreteCommand {
    private static final AuthenticationCommand AUTHENTICATION_COMMAND = new AuthenticationCommand();

    private static final AuthenticationExecutor AUTHENTICATION_EXECUTOR = AuthenticationExecutorImpl.getInstance();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillContentCommand.getInstance();
    private static final int FIRST_POSITION = 0;

    private AuthenticationCommand() {

    }

    public static AuthenticationCommand getInstance() {
        return AUTHENTICATION_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        UserDataDTO userDataDTO;
        String email = requestHolder.getSingleRequestParameter(FIRST_POSITION, EMAIL);
        String password = requestHolder.getSingleRequestParameter(FIRST_POSITION, PASS);
        try {
            userDataDTO = AUTHENTICATION_EXECUTOR.checkUserAccessory(email, password);
            if (userDataDTO.getUserExemplar() != null) {
                requestHolder.addSessionAttribute(ROLE, userDataDTO.getUserExemplar().getRole().getValue());
                requestHolder.addSessionAttribute(USER_INFO, userDataDTO.getUserExemplar());
            }
            FILL_VACANCY_COMMAND.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(INFO_MESSAGE, userDataDTO.getInfoMessage());
    }
}
