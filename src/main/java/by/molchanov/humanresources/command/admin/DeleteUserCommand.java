package by.molchanov.humanresources.command.admin;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.DeleteCloseExecutor;
import by.molchanov.humanresources.executor.impl.DeleteCloseExecutorImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.USER_ID;

/**
 * Class {@link DeleteUserCommand} is used for delete user from system.
 *
 * @author MolcanovVladislav
 * @see ConcreteCommand
 */
public class DeleteUserCommand implements ConcreteCommand {
    private static final DeleteUserCommand DELETE_USER_COMMAND = new DeleteUserCommand();
    private static final ConcreteCommand FILL_CONTENT_COMMAND = FillContentCommand.getInstance();
    private static final DeleteCloseExecutor DELETE_EXECUTOR = DeleteCloseExecutorImpl.getInstance();

    private DeleteUserCommand() {

    }

    public static DeleteUserCommand getInstance() {
        return DELETE_USER_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<String> usersId = new ArrayList<>(Arrays.asList(requestHolder.getRequestParameter(USER_ID)));
        if (!usersId.isEmpty()) {
            try {
                DELETE_EXECUTOR.deleteUser(usersId);
            } catch (CustomExecutorException e) {
                throw new CustomBrokerException(e);
            }
        }
        FILL_CONTENT_COMMAND.execute(requestHolder);
    }
}
