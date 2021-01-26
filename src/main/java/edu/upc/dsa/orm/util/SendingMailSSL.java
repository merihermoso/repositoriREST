package edu.upc.dsa.orm.util;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendingMailSSL {

    public SendingMailSSL() {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("firefighteradventure", "Mazinger72");
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("firefighteradventure@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("xluisog30x@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Estimado cliente," +
                    "\n\n Le damos la bienvenida mediante SSL!");
            Transport.send(message);
            System.out.println("Correcto!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}