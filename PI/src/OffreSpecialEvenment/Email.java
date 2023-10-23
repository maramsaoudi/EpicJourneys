import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    public static void main(String args[]) {
        final String username = "achraf.boubaker@esprit.tn";
        final String password = "n7ebnmout";

        Properties props  = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");

        Session session = Session.getInstance(props, 
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
        });           

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("achraf.boubaker@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("achraf.boubaker@esprit.tn"));
            message.setSubject("First");
            message.setContent("<h1>hello</h1>","text/html");
            Transport.send(message);

            System.out.println("Email has been sent succesfully...");   
        }
        catch(MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
