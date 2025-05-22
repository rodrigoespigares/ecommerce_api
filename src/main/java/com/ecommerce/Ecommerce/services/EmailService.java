package com.ecommerce.Ecommerce.services;
import com.ecommerce.Ecommerce.components.EmailProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

@Service
public class EmailService {
    @Autowired
    private EmailProperty emailProperty;

    public void sendEmail(String to, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", String.valueOf(emailProperty.isAuth()));
        props.put("mail.smtp.starttls.enable", String.valueOf(emailProperty.isStarttls()));
        props.put("mail.smtp.host", emailProperty.getHost()); // Sin String.valueOf()
        props.put("mail.smtp.port", String.valueOf(emailProperty.getPort()));
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2 TLSv1.3");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProperty.getUsername(), emailProperty.getPassword());
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailProperty.getUsername(), "Pockommers"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body); // o usa setContent(body, "text/html") si quieres HTML
            Transport.send(message);
            System.out.println("Email enviado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al enviar email: " + e.getMessage());
        }
    }
}
