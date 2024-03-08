package tn.esprit.services;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class SmsTwilio{
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC44693c1a0e3bd2bc183ac5a895f16127";
    public static final String AUTH_TOKEN = "8da447634fbc33965d005faeb6cbd294";

    public  void SendSms() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+21692080677"),
                        new com.twilio.type.PhoneNumber("+12539449022"),
                        "Votre reservation est effectuez avec succes !!!")
                .create();

        System.out.println(message.getSid());
    }
}