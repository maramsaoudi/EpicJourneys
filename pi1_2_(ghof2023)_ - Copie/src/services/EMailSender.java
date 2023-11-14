/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import gui.MdpOubliéUserController;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ANIS
 */
public class EMailSender {
  
    public static void sendEmail(String from, String password, String to, String subject, String body) {

        // Set properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        // Create a new email session with the properties
        Session session = Session.getInstance(properties,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("ghofranetayari61@gmail.com", "ugve iuxe xqth ffmx");
                }
            });

        try {
            
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ghofranetayari61@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("code verification");
           // message.setText("123");
          /* int code = 123; // Remplacez 123 par votre code
String codeAsString = String.valueOf(code); // Convertir l'entier en chaîne de caractères
message.setText(codeAsString); */// Utiliser la chaîne de caractères dans le corps de l'e-mail

// Utilisez la variable 'code' ici au lieu de la valeur codée en dur
        message.setText(String.valueOf(MdpOubliéUserController.code));






            // Send the email message
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }   
}
