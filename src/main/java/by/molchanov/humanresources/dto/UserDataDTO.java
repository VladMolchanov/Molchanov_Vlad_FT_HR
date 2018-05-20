package by.molchanov.humanresources.dto;

import by.molchanov.humanresources.entity.User;

/**
 * Class {@link UserDataDTO} is used for transfer data about user between command and service level.
 *
 * @author MolcanovVladislav
 */
public class UserDataDTO {
    private User userExemplar;
    private String infoMessage;
    private String repeatPassword;

    public User getUserExemplar() {
        return userExemplar;
    }

    public void setUserExemplar(User userExemplar) {
        this.userExemplar = userExemplar;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
