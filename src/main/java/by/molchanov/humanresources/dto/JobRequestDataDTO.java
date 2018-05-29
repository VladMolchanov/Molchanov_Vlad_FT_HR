package by.molchanov.humanresources.dto;

import by.molchanov.humanresources.entity.JobRequest;

/**
 * Class {@link JobRequestDataDTO} is used for transfer data about job request between command and service level.
 *
 * @author Molchanov Vladislav
 */
public class JobRequestDataDTO {
    private JobRequest jobRequest;
    private String infoMessage;
    private int vacancyId;

    public JobRequest getJobRequest() {
        return jobRequest;
    }

    public void setJobRequest(JobRequest jobRequest) {
        this.jobRequest = jobRequest;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }
}
