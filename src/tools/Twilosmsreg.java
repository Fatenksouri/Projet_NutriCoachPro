/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author Kardo
 */
public class Twilosmsreg {
    
 // Your Twilio credentials and phone number
    public static final String ACCOUNT_SID = "AC1fc6f7e38347f5e276547b9c6711b0c9";
    public static final String AUTH_TOKEN = "cfe181c150dcc721b0bb96e859db91b2";
    public static final String FROM_PHONE_NUMBER = "+17793793901";

    public static void sendSMS(String toPhoneNumber, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
            new PhoneNumber(toPhoneNumber),
            new PhoneNumber(FROM_PHONE_NUMBER),
            messageBody)
            .create();

        System.out.println("SMS sent with SID: " + message.getSid());
    }
}
