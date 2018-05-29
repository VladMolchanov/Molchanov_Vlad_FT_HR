package by.molchanov.humanresources.dto;

import java.io.Serializable;

public class MessageDataDTO implements Serializable {
    private String messageText;
    private String messageTheme;
    private String receiverEmail;
    private String infoMessage;
    private String vacancyName;

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageTheme() {
        return messageTheme;
    }

    public void setMessageTheme(String messageTheme) {
        this.messageTheme = messageTheme;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }
}
