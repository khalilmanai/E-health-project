package com.example.app.services;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsTwilio1 {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "ACad42447d0ab59b8210657ac8d03bfa8a";
    public static final String AUTH_TOKEN = "edfd455162b52f9c91257a780ea7ee7f";

    public void SendSms() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber("+21655223426"),
                        new PhoneNumber("+15129603596"),
                        "Your message")
                .create();

        System.out.println(message.getSid());
    }
}