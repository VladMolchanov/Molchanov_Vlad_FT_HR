package by.molchanov.humanresources.command.aspirant;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.common.FillContentCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.JobRequestDataDTO;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.RegistrationExecutor;
import by.molchanov.humanresources.executor.impl.RegistrationExecutorImpl;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;

/**
 * Class {@link JobRequestRegistrationCommand} is used for new request registration.
 *
 * @author MolcanovVladislav
 * @see ConcreteCommand
 */
public class JobRequestRegistrationCommand implements ConcreteCommand {
    private static final JobRequestRegistrationCommand JOB_REQUEST_REGISTRATION_COMMAND = new JobRequestRegistrationCommand();
    private static final RegistrationExecutor REGISTRATION_EXECUTOR = RegistrationExecutorImpl.getInstance();
    private static final ConcreteCommand FILL_VACANCY_COMMAND = FillContentCommand.getInstance();

    private static final int FIRST_INDEX = 0;

    private JobRequestRegistrationCommand() {

    }

    public static JobRequestRegistrationCommand getInstance() {
        return JOB_REQUEST_REGISTRATION_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        String resume = requestHolder.getSingleRequestParameter(FIRST_INDEX, REQUEST_RESUME);
        int vacancyId = Integer.parseInt(requestHolder.getSingleRequestParameter(FIRST_INDEX, VACANCY_ID));
        User user = (User) requestHolder.getSessionAttribute(USER_INFO);
        JobVacancy jobVacancy = new JobVacancy();
        jobVacancy.setId(vacancyId);
        JobRequest jobRequest = new JobRequest();
        jobRequest.setResume(resume);
        jobRequest.setUser(user);
        jobRequest.setJobVacancy(jobVacancy);
        JobRequestDataDTO jobRequestDataDTO = new JobRequestDataDTO();
        jobRequestDataDTO.setJobRequest(jobRequest);
        try {
            REGISTRATION_EXECUTOR.requestSignUp(jobRequestDataDTO);
            FILL_VACANCY_COMMAND.execute(requestHolder);
        } catch (CustomExecutorException e) {
            throw new CustomBrokerException(e);
        }
        requestHolder.addRequestAttribute(INFO_MESSAGE, jobRequestDataDTO.getInfoMessage());
    }
}
