/* package tn.esprit.api;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private static Session session;

    public static void sendEmail(String username, String password, String to, String subject, String content) {

        // Mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Session creation
        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            // Set the sender's email address
            message.setFrom(new InternetAddress(username));
            // Set recipient's email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // Set email subject
            message.setSubject(subject);
            // Set email content
            message.setText(content);
            // Set sent date
            message.setSentDate(new Date());

            // Send the email message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
*/