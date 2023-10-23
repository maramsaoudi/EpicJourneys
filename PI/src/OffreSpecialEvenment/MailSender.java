package OffreSpecialEvenment;

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

public class MailSender {

    public static void sendMail(String recipient) throws Exception {
        System.out.println("Preparing to send an email.");

        Properties props = new Properties(); 
        props.put("mail.debug", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        String myAccountEmail = "achraf.boubaker@esprit.tn";
        String password = "n7ebnmout"; 

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
message.setSubject("Invitation Exclusive à un Événement Spécial pour Vous");

// Replace these placeholders with actual dynamic values
String nomDuClient = "M. Dupont";
String dateEvenement = "15 octobre 2023";
String heureEvenement = "18h00";
String lieuEvenement = "Salle de réception élégante";
String programmeEvenement = "Réception, dîner, et divertissements spéciaux";

message.setText("Cher " + nomDuClient + ",\n\n"
  + "J'espère que cette lettre vous trouve en bonne santé et de bonne humeur. Nous sommes ravis de vous compter parmi nos clients fidèles, et nous tenons à vous remercier pour votre confiance continue envers [Nom de l'Entreprise].\n\n"
  + "En reconnaissance de votre fidélité, nous avons le plaisir de vous inviter à un événement spécial qui est spécialement organisé pour vous. Vous êtes important pour nous, et cet événement est notre façon de vous remercier.\n\n"
  + "Détails de l'Événement :\n"
  + "- Date : " + dateEvenement + "\n"
  + "- Heure : " + heureEvenement + "\n"
  + "- Lieu : " + lieuEvenement + "\n"
  + "- Programme : " + programmeEvenement + "\n\n"
  + "Nous avons préparé une soirée mémorable avec des moments uniques et des surprises réservées exclusivement à nos clients les plus appréciés. Ce sera l'occasion parfaite de célébrer notre partenariat et de vous montrer combien vous comptez pour nous.\n\n"
  + "Nous aimerions beaucoup votre présence à cet événement, et nous espérons que vous pourrez vous joindre à nous. Veuillez confirmer votre présence en répondant à ce courrier électronique ou en appelant notre service client au [Numéro de Téléphone]. Nous serons heureux de vous assister dans l'organisation de votre venue.\n\n"
  + "Nous attendons avec impatience de passer une soirée inoubliable en votre compagnie. Votre présence serait une grande joie pour nous.\n\n"
  + "Encore une fois, merci de votre fidélité envers [Nom de l'Entreprise]. Nous espérons vous voir à notre événement spécial.\n\n"
  + "Avec nos meilleures salutations,\n"
  + "[Votre Nom]\n"
  + "[Votre Titre]\n"
  + "[Nom de l'Entreprise]\n"
  + "[Coordonnées de Contact]");
return message;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
