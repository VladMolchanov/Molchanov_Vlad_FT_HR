package by.molchanov.humanresources.command.admin;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.ConfirmExecutor;
import by.molchanov.humanresources.executor.impl.ConfirmExecutorImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.USER_ID;

/**
 * Class {@link RiseToAdminCommand} makes admin from user in system.
 *
 * @author Molchanov Vladislav
 * @see  ConcreteCommand
 */
public class RiseToAdminCommand implements ConcreteCommand {
    private static final RiseToAdminCommand RISE_TO_ADMIN_COMMAND = new RiseToAdminCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();
    private ConfirmExecutor confirmExecutor = ConfirmExecutorImpl.getInstance();

    private RiseToAdminCommand() {

    }

    public static RiseToAdminCommand getInstance() {
        return RISE_TO_ADMIN_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        List<String> usersId = new ArrayList<>(Arrays.asList(requestHolder.getRequestParameter(USER_ID)));
        if (!usersId.isEmpty()) {
            try {
                confirmExecutor.riseToAdmin(usersId);
            } catch (CustomExecutorException e) {
                throw new CustomBrokerException(e);
            }
        }
        fillContentCommand.execute(requestHolder);
    }
}
