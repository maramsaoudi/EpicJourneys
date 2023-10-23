/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.api;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
    public static final String ACCOUNT_SID = "AC836eab96a74c077c9c1f66979efa2999";
    public static final String AUTH_TOKEN = "86f53cbc5000365e6cf0c5b3e29f9ef8";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Replace these with your own values
        String fromPhoneNumber = "+16307492027";  // Your Twilio phone number
        String toPhoneNumber = "+21650759756";    // Recipient's phone number
        String messageBody = "Hello, this is a test message from Twilio!";

        Message message = Message.creator(
            new PhoneNumber(toPhoneNumber),
            new PhoneNumber(fromPhoneNumber),
            messageBody
        ).create();

        System.out.println("SMS sent with SID: " + message.getSid());
    }
}

