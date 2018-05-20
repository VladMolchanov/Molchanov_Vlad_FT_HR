package by.molchanov.humanresources.dto;

import by.molchanov.humanresources.entity.JobRequest;

public class JobRequestDTO {
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
