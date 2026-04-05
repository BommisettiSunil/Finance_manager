package servlet;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
	public static void sendEmail(String toEmail, String subject, String text) {

        String fromEmail = "bommisettisunil241@gmail.com";
        String password = "vespsaffjblqdkwm";

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(fromEmail));

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            message.setSubject(subject);

            message.setText(text);

            Transport.send(message);

            System.out.println("Email Sent Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
