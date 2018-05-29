package by.molchanov.humanresources.dto;

import by.molchanov.humanresources.entity.User;

import java.io.Serializable;

/**
 * Class {@link OrgDataDTO} is used for transfer data about organization between command and service level.
 *
 * @author Molchanov Vladislav
 */
public class OrgDataDTO implements Serializable {
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

