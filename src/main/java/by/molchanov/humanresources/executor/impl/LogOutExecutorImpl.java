package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.executor.LogOutExecutor;

import java.util.ArrayList;
import java.util.List;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;

public class LogOutExecutorImpl implements LogOutExecutor {
    private static final LogOutExecutorImpl LOG_OUT_EXECUTOR = new LogOutExecutorImpl();

    private LogOutExecutorImpl() {

    }

    public static LogOutExecutorImpl getInstance() {
        return LOG_OUT_EXECUTOR;
    }

    public List<String> logOut() {
        List<String> attributeForDelete = new ArrayList<>();
        attributeForDelete.add(ROLE);
        attributeForDelete.add(USER_INFO);
        return attributeForDelete;
    }
}
