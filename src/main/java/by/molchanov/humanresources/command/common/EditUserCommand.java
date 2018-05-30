package by.molchanov.humanresources.command.common;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.UserDataDTO;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.EditExecutor;
import by.molchanov.humanresources.executor.impl.EditExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;

/**
 * Class {@link EditUserCommand} is used for making updates to user.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class EditUserCommand implements ConcreteCommand {
    private static final EditUserCommand EDIT_USER_COMMAND = new EditUserCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();
    private EditExecutor editExecutor = EditExecutorImpl.getInstance();

    private static final int FIRST_INDEX = 0;

    private EditUserCommand() {

    }

    public static EditUserCommand getInstance() {
        return EDIT_USER_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        User user = (User) requestHolder.getSessionAttribute(USER_INFO);
        String firstName = requestHolder.getSingleRequestParameter(FIRST_INDEX, FIRST_NAME);
        String lastName = requestHolder.getSingleRequestParameter(FIRST_INDEX, LAST_NAME);
        String email = requestHolder.getSingleRequestParameter(FIRST_INDEX, EMAIL);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        UserDataDTO userDataDTO = new UserDataDTO();
        userDataDTO.setUserExemplar(user);
        userDataDTO.setAltEmail(email);
        try {
            editExecutor.editUser(userDataDTO);
            fillContentCommand.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(INFO_MESSAGE, userDataDTO.getInfoMessage());
    }
}
