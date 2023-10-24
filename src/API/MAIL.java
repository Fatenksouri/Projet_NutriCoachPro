/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;

/**
 *
 * @author Asus
 */
public class MAIL {
        private static final String USERNAME = "servicenutricoachpro@gmail.com";
    private static final String PASSWORD = "fgfwxfdgmuntfnuo";

    /*public static void envoyer(String email) throws Exception{
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        Message message = prepareMessage(session, email);
        Transport.send(message);
        System.out.println("Message envoyé avec succès.");
    }
    
    private static Message prepareMessage(Session session, String email){ 
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("PIDEV Activite ");                
            message.setText("Bonjour merci de nos aider a creer des planning ");
            return message;
        } catch (MessagingException e) {
            Logger.getLogger(MAIL.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }*/
    public static void envoyer(String email, String subject, String message) throws Exception {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.ssl.trust", "*");

    Session session = Session.getInstance(props, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(USERNAME, PASSWORD);
        }
    });

    Message mailMessage = prepareMessage(session, email, subject, message);

    Transport.send(mailMessage);
    System.out.println("Message envoyé avec succès.");
}

private static Message prepareMessage(Session session, String email, String subject, String content) {
    try {
        Message mailMessage = new MimeMessage(session); // Renommé la variable message en mailMessage
        mailMessage.setFrom(new InternetAddress(USERNAME));
        mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        return mailMessage;
    } catch (MessagingException e) {
        Logger.getLogger(MAIL.class.getName()).log(Level.SEVERE, null, e);
        return null;
    }
}

}



