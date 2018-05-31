package by.molchanov.humanresources.controller;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.ResponseType;
import by.molchanov.humanresources.database.ConnectionPool;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.factory.OperationFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.COMMAND;

/**
 * Class {@link Controller} is the main servlet of application. Accepts requests and sends responses.
 *
 * @author Molchanov Vladislav
 * @see HttpServlet
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String MAIN_PAGE = AddressPageConfiguration.getInstance().getMainPageAddress();

    private static final int FIRST_INDEX = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handleRequest(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OperationFactory operationFactory = OperationFactory.getInstance();
        RequestHolder requestHolder = new RequestHolder(request);
        String requestCommand = requestHolder.getSingleRequestParameter(FIRST_INDEX, COMMAND);
        ConcreteCommand command = operationFactory.getConcreteCommand(requestCommand);
        ResponseType responseType = operationFactory.getResponseType(requestCommand);
        try {
            command.execute(requestHolder);
            requestHolder.update(request);
        } catch (CustomBrokerException e) {
            LOGGER.warn(e.getMessage(), e);
            responseType = ResponseType.REDIRECT;
        }
        if (responseType == ResponseType.FORWARD) {
            getServletContext().getRequestDispatcher(MAIN_PAGE).forward(request, response);
        } else if (responseType == ResponseType.REDIRECT) {
            response.sendRedirect(MAIN_PAGE);
        }
    }
}
