

package utils;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


/**
 *
 * @author omnia
 
public class Mailling {
    

public Mailling(){}

public void sendemail(String mail)
{
Properties prop = new Properties();

prop.put ("mail.smtp.auth", "true");
prop.setProperty("mail.smtp.starttls.enable", "true");
prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
prop.put ("mail.smtp.host", "smtp.gmail.com");
prop.put ("mail.smtp.port", "587");
Session s = Session.getInstance(prop, new Authenticator() {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("servicenutricoachpro@gmail.com","fgfwxfdgmuntfnuo");
    }
});
try {
Message message = new MimeMessage(s);
message.setFrom(new InternetAddress("servicenutricoachpro@gmail.com"));
message.setRecipients(
  Message.RecipientType.TO, InternetAddress.parse(mail));
message.setSubject("Résultat demande de convention avec NutriCoach Pro");

//String msg ="Félicitations! Votre demande de convention avec NutriCoach Pro a été acceptée.\n Merci de nous fournir les offres que vous proposez pour les publier dans notre catalogue.\n Cordialement, NutriCoach Pro.";
String msg ="njarbou fi faten 5alli nzidou nfadidouha ha lila";

MimeBodyPart mimeBodyPart = new MimeBodyPart();
mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

Multipart multipart = new MimeMultipart();
multipart.addBodyPart(mimeBodyPart);

message.setContent(multipart);

Transport.send(message);
}catch (MessagingException ex) {
 System.err.println(ex.getMessage());
}
}



     


}