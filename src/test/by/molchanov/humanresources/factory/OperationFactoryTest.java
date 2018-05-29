package by.molchanov.humanresources.factory;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.ResponseType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OperationFactoryTest {
    @Test
    public void getConcreteCommandTest() {
        OperationFactory operationFactory = OperationFactory.getInstance();
        String command = "log_out";
        ConcreteCommand concreteCommand;
        concreteCommand = operationFactory.getConcreteCommand(command);
        Assert.assertNotNull(concreteCommand);
    }

    @Test
    public void getResponseTypeTest() {
        OperationFactory operationFactory = OperationFactory.getInstance();
        String command = "log_out";
        ResponseType expectedResponseType = ResponseType.FORWARD;
        ResponseType receivedResponseType;
        receivedResponseType = operationFactory.getResponseType(command);
        Assert.assertEquals(expectedResponseType, receivedResponseType);
    }
}
