package services;

import com.sun.xml.internal.ws.resources.XmlmessageMessages;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EMailSender {

    public static void sendMail(String recipient) throws Exception {
        System.out.println("Preparing to send an email.");

        Properties props = new Properties(); 
        props.put("mail.debug", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        String myAccountEmail = "gghofrane076@gmail.com";
        String password = "123456789ab,"; 

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        } 
        );
        session.setDebug(true);


        Message message = prepareMessage(session, myAccountEmail, recipient);
        if (message != null) {
            Transport.send(message);
            System.out.println("Email sent successfully.");
        }
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            System.out.println("Trying to send email.");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Subject of the email");
            message.setText("Hello,\nThis is the email content.\nRegards.");
            return message;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void sendEmail(String ghofranetayariesprittn, String cin22484270, String text, String verification_code, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
