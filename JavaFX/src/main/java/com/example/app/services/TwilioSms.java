package com.example.app.services;


import com.example.app.models.Facturation;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;
import java.util.Calendar;

public class TwilioSms {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "ACc912c62fb095c2ecc2898f1c7d1cf5ea";
    public static final String AUTH_TOKEN = "6e895a8bef9017b0cdf7627f5055b72c";

    public void Sendsms(Facturation f) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(f.getDate_emission());

        // Extracting day, month, and year
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based, so we add 1
        int year = calendar.get(Calendar.YEAR);
        String dateF=String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber("+21692775587"),
                        new PhoneNumber("+19497103943"),
                        "Nouvelle Facture ajouter : Numero de facture : "+f.getNum_facture()+"    Date 'emission : "+ dateF +"  Montant_Totale "+f.getMontant_tot())
                .create();

        System.out.println(message.getSid());
    }
}