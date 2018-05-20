package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.SendMessageExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

/**
 * Class {@link SendMessageExecutorImpl} used for sending message.
 *
 * @author MolcanovVladislav
 * @see SendMessageExecutor
 */
public class SendMessageExecutorImpl implements SendMessageExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final SendMessageExecutorImpl SEND_MESSAGE_EXECUTOR = new SendMessageExecutorImpl();

    private static final String SIGNATURE = "\n place for signature!!!";

    private SendMessageExecutorImpl() {

    }

    public static SendMessageExecutorImpl getInstance() {
        return SEND_MESSAGE_EXECUTOR;
    }

    @Override
    public void sendRequestAnswer(String messageTheme, String message, String aspirantEmail) throws CustomExecutorException {
        message += SIGNATURE;
        sendMessage(messageTheme, message, aspirantEmail);
    }

    public void sendMessage(String messageTheme, String message, String receiver) throws CustomExecutorException {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.put("mail.smtp.debug", "true");

//         creates a new session with an authenticator
//        Authenticator auth = new Authenticator() {
//            @Override
//            public PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("vladd1997xx@gmail.com", "3129093529q");
//            }
//        };
//
//
//        Session session = Session.getInstance(properties, auth);
//        // creates a new e-mail message
//        Message msg = new MimeMessage(session);
//        try {
//            msg.setFrom(new InternetAddress("vladd1997xx@gmail.com"));
//            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver) );
//            msg.setSubject(messageTheme);
//            msg.setContent(message, "text/html;charset=UTF-8");
//            "text/html;charset=UTF-8"
//            Transport.send(msg);
//        } catch (MessagingException e) {
//            LOGGER.info(e);
//            throw new CustomExecutorException(e);
//        }

//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//        props.put("mail.smtp.debug", "true");
//        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//        Session session = Session.getInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("vladd1997xx@gmail.com", "3129093529q");
//            }
//        });
//
//        try {
//            Message messag = new MimeMessage(session);
//            //от кого
//            messag.setFrom(new InternetAddress("vladd1997xx@gmail.com"));
//            //кому
//            messag.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
//            //Заголовок письма
//            messag.setSubject(messageTheme);
//            //Содержимое
//            messag.setText(message);
//
//            //Отправляем сообщение
//            Transport.send(messag);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }

//        return 1;
    }
}
