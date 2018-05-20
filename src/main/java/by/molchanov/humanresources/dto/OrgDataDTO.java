package by.molchanov.humanresources.dto;

import by.molchanov.humanresources.entity.User;

public class OrgDataDTO {
    private User orgDirector;
    private String infoMessage;

    public User getOrgDirector() {
        return orgDirector;
    }

    public void setOrgDirector(User orgDirector) {
        this.orgDirector = orgDirector;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }
}

