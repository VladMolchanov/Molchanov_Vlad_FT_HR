package by.molchanov.humanresources.command.aspirant;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.OrgDataDTO;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.OrganizationType;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.RegistrationExecutor;
import by.molchanov.humanresources.executor.impl.RegistrationExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;
import static by.molchanov.humanresources.command.SessionRequestAttributeName.TYPE;

/**
 * Class {@link OrgRegistrationCommand} is used for new organization registration.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class OrgRegistrationCommand implements ConcreteCommand {
    private static final OrgRegistrationCommand ORG_REGISTRATION_COMMAND = new OrgRegistrationCommand();
    private RegistrationExecutor registrationExecutor = RegistrationExecutorImpl.getInstance();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();

    private static final int FIRST_INDEX = 0;

    private OrgRegistrationCommand() {

    }

    public static OrgRegistrationCommand getInstance() {
        return ORG_REGISTRATION_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        OrgDataDTO orgDataDTO = new OrgDataDTO();
        String name = requestHolder.getSingleRequestParameter(FIRST_INDEX, ORG_NAME);
        String website = requestHolder.getSingleRequestParameter(FIRST_INDEX, WEBSITE);
        String description = requestHolder.getSingleRequestParameter(FIRST_INDEX, DESCRIPTION);
        String type = requestHolder.getSingleRequestParameter(FIRST_INDEX, TYPE);
        User orgDirector = (User) requestHolder.getSessionAttribute(USER_INFO);
        Organization organization = new Organization();
        organization.setName(name);
        organization.setWebsite(website);
        organization.setDescription(description);
        organization.setType(OrganizationType.valueOf(type.toUpperCase()));
        orgDirector.setOrganization(organization);
        orgDataDTO.setOrgDirector(orgDirector);
        try {
            registrationExecutor.orgSignUp(orgDataDTO);
            requestHolder.addSessionAttribute(ROLE, orgDataDTO.getOrgDirector().getRole().getValue());
            requestHolder.addSessionAttribute(USER_INFO, orgDataDTO.getOrgDirector());
            fillContentCommand.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
    }
}
