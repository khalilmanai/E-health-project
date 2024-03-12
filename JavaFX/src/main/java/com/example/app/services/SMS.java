package com.example.app.services;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;
public class SMS {



        // Find your Account Sid and Token at twilio.com/console
        public static final String ACCOUNT_SID = "ACc912c62fb095c2ecc2898f1c7d1cf5ea";
    public static final String AUTH_TOKEN = "6e895a8bef9017b0cdf7627f5055b72c";


       public void sms(String x) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new PhoneNumber("+21692775587" ),
                            new PhoneNumber("+19497103943"),

                            "Prix total produit :"+x)
                    .create();

            System.out.println(message.getSid());
        }
    }

