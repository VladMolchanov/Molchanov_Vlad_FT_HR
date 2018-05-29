package by.molchanov.humanresources.command.director;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.VacancyDataDTO;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.RegistrationExecutor;
import by.molchanov.humanresources.executor.impl.RegistrationExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;

/**
 * Class {@link VacancyRegistrationCommand} is used for new vacancy registration.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class VacancyRegistrationCommand implements ConcreteCommand {
    private static final VacancyRegistrationCommand VACANCY_REGISTRATION_COMMAND = new VacancyRegistrationCommand();
    private RegistrationExecutor registrationExecutor = RegistrationExecutorImpl.getInstance();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();
    private static final int FIRST_INDEX = 0;

    private VacancyRegistrationCommand() {

    }

    public static VacancyRegistrationCommand getInstance() {
        return VACANCY_REGISTRATION_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String vacancyName = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_NAME);
        String vacancyRequirement = requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_REQUIREMENT);
        User user = (User) requestHolder.getSessionAttribute(USER_INFO);
        Organization organization = user.getOrganization();
        JobVacancy jobVacancy = new JobVacancy();
        jobVacancy.setOrganization(organization);
        jobVacancy.setName(vacancyName);
        jobVacancy.setRequirement(vacancyRequirement);
        VacancyDataDTO vacancyDataDTO = new VacancyDataDTO();
        vacancyDataDTO.setJobVacancy(jobVacancy);
        try {
            registrationExecutor.vacancySignUp(vacancyDataDTO);
            fillContentCommand.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(INFO_MESSAGE, vacancyDataDTO.getInfoMessage());
    }
}
