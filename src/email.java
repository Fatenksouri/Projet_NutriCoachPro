import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class email {

    public static void sendOfferConfirmation(String userEmail, String offerDetails) {
        final String username = "your-email@gmail.com"; // Votre adresse e-mail Gmail
        final String password = "your-password"; // Votre mot de passe Gmail

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Serveur SMTP Gmail
        props.put("mail.smtp.port", "587"); // Port SMTP Gmail

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Confirmation d'ajout d'une nouvelle offre");
            message.setText("Vous avez ajouté une nouvelle offre :\n\n" + offerDetails);

            Transport.send(message);

            System.out.println("E-mail de confirmation envoyé avec succès.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'envoi de l'e-mail de confirmation.");
        }
    }

    public static void main(String[] args) {
        String userEmail = "chadha.jaafra@esprit.tn"; // Adresse e-mail de réception
        String offerDetails = "Détails de l'offre :\n" +
            "Nom de l'offre : Offre de test\n" +
            "Date de l'offre : 2023-10-25\n" +
            "Heure de début : 08:00\n" +
            "Heure de fin : 16:00\n" +
            "Prix : 50.0\n" +
            "Description : Offre de test pour l'exemple\n" +
            "Type : Test";

        sendOfferConfirmation(userEmail, offerDetails);
    }
}
