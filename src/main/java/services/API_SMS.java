package services;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

import java.net.URI;
import java.math.BigDecimal;

    public class API_SMS {
        public static final String ACCOUNT_SID = "ACe97600cc5fef1ee52a9c3a03f3bff764";
        public static final String AUTH_TOKEN = "5f428f2988f441d4982eb67cd1173c1d";

        public  void sendSMS(String nom) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber("+21629319685"),
                            new com.twilio.type.PhoneNumber("+17178077579"),
                            nom+ " Sont insufisant ")
                    .create();

            System.out.println(message.getSid());
        }
}
