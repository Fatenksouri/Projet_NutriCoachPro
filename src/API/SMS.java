/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;


import com.twilio.rest.api.v2010.account.Message;

import com.twilio.Twilio;



/**
 *
 * @author Asus
 */
public class SMS {
  private final String ACCOUNT_SID = "AC9101d172b93a385256c3af1c983faa53";
    private final String AUTH_TOKEN = "11b84538937b77e764419ea18cb07eca";
    private final String TWILIO_PHONE_NUMBER = "+17793244242";

    public SMS() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendSMS() {
      Message create = Message.creator(
              new com.twilio.type.PhoneNumber("+21629226163"),
              new com.twilio.type.PhoneNumber(TWILIO_PHONE_NUMBER), "ibtihel ").create();
    }
    
}
