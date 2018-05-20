package by.molchanov.humanresources.command;

import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.exception.CustomBrokerException;

public interface ConcreteCommand {
    void execute(RequestHolder requestHolder) throws CustomBrokerException;
}
