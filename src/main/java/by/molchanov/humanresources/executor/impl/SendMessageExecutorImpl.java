package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dto.MessageDataDTO;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.SendMessageExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static by.molchanov.humanresources.executor.PropertyMessageVariablesName.MESSAGE_CORRECT_SEND;
import static by.molchanov.humanresources.executor.PropertyMessageVariablesName.MESSAGE_INCORRECT_FORMAT;
import static by.molchanov.humanresources.validator.VacancyRequestDataValidation.isTextCorrect;
import static by.molchanov.humanresources.validator.VacancyRequestDataValidation.isVacancyNameCorrect;

/**
 * Class {@link SendMessageExecutorImpl} used for sending message.
 *
 * @author MolcanovVladislav
 * @see SendMessageExecutor
 */
public class SendMessageExecutorImpl implements SendMessageExecutor {
    private static final SendMessageExecutorImpl SEND_MESSAGE_EXECUTOR = new SendMessageExecutorImpl();
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String SMTP_CONFIGURATION_FILE_NAME = "smtp_configuration.properties";
    private static final String EMAIL_CONFIGURATION_FILE_NAME = "email_configuration.properties";
    private String applicationEmail;
    private String applicationEmailPass;
    private Properties smtpProperties;

    private static final String SIGNATURE = "\n\n #application#human#resources";
    private static final String SEPARATOR = " // ";

    private SendMessageExecutorImpl() {
        smtpProperties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(SMTP_CONFIGURATION_FILE_NAME)) {
            smtpProperties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Properties file opening error!", e);
        }
        Properties emailProperties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(EMAIL_CONFIGURATION_FILE_NAME)) {
            emailProperties.load(inputStream);
            applicationEmail = emailProperties.getProperty("application.email");
            applicationEmailPass = emailProperties.getProperty("application.email.pass");
        } catch (IOException e) {
            LOGGER.error("Properties file opening error!", e);
        }
    }

    public static SendMessageExecutorImpl getInstance() {
        return SEND_MESSAGE_EXECUTOR;
    }

    @Override
    public void sendRequestAnswer(MessageDataDTO messageDataDTO) throws CustomExecutorException {
        String messageTheme = messageDataDTO.getMessageTheme();
        String messageText = messageDataDTO.getMessageText();
        String receiver = messageDataDTO.getReceiverEmail();
        String vacName = messageDataDTO.getVacancyName();
        if (!isVacancyNameCorrect(messageTheme) || !isTextCorrect(messageText)){
            messageDataDTO.setInfoMessage(MESSAGE_INCORRECT_FORMAT);
        } else {
            messageTheme += SEPARATOR;
            messageTheme += vacName;
            messageText += SIGNATURE;
            sendMessage(messageTheme, messageText, receiver);
            messageDataDTO.setInfoMessage(MESSAGE_CORRECT_SEND);
        }
    }

    private void sendMessage(String messageTheme, String message, String receiver) throws CustomExecutorException {

        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(applicationEmail, applicationEmailPass);
            }
        };

        Session session = Session.getInstance(smtpProperties, auth);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(applicationEmail));
            InternetAddress[] toAddresses = { new InternetAddress(receiver) };
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(messageTheme);
            msg.setSentDate(new Date());
            msg.setText(message);
            Transport.send(msg);
        } catch (MessagingException e) {
            throw new CustomExecutorException("Send message error!", e);
        }
    }
}
