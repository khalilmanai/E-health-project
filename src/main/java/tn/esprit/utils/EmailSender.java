package tn.esprit.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_USERNAME = "bahraouiabir4@gmail.com";
    private static final String SMTP_PASSWORD = System.getenv("SMTP_PASSWORD"); // Read password from environment variable
    private static final int SMTP_PORT = 587; // Gmail SMTP port


    public static void Email(String to) {
        String subject = "NutriNet - Confirmation d'ajout";
        String body  = "Cher Utilisateur,\n\n"
                + "Nous sommes ravis de vous informer que votre demande d'ajout d'un nouveau restaurant sur notre plateforme a été traitée avec succès.\n" +
                "\n\n\n"
                + "Votre restaurant est désormais visible sur notre plateforme et accessible aux utilisateurs en quête de délices culinaires. Nous vous remercions pour votre contribution à enrichir notre communauté.\n" +
                "\n.\n\n"
                + "N'hésitez pas à nous contacter si vous avez des questions ou besoin d'assistance supplémentaire.\n" +
                "\n.\n" +
                "\n.\n\n"
                + "Cordialement,\n"
                + "L'équipe de NutriNET";

        sendEmail(SMTP_USERNAME, to, subject, body);
    }

    public static void sendEmail(String from, String to, String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
    }
}
