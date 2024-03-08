package tn.esprit.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

public class EmailSender {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_USERNAME = "medkhalilmannai@gmail.com";
    private static final String SMTP_PASSWORD = "qzjr evzq dcot guym";
    private static final int SMTP_PORT = 587; // Adjust port number as needed
    private static String code;

    public static void VerificationCodeSender(String to) {

        String subject = "NutriNet@NutriNet ";
        String body  = "Cher client,:\n\n"
                + "Nous sommes ravis de vous informer que votre demande d'ajout d'un nouveau restaurant sur notre plateforme a été traitée avec succès.\n" +
                "\n\n\n"
                + "Votre restaurant est désormais visible sur notre plateforme et accessible aux utilisateurs en quête de délices culinaires. Nous vous remercions pour votre contribution à enrichir notre communauté.\n" +
                "\n.\n\n"
                + "N'hésitez pas à nous contacter si vous avez des questions ou besoin d'assistance supplémentaire.\n" +
                "\n.\n"
                + "Cordialement,\n"
                + "L'équipe de NutriNET";


        sendEmail(SMTP_USERNAME, to, subject, body);

    }


    public String getVerificationCode(){
        return code;
    }


    private static String generateVerificationCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static void sendEmail(String from, String to, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new Authenticator() {
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

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
    }
}
