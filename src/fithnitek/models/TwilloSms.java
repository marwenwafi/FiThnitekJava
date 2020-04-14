/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author yassin
 */
public class TwilloSms {
 public static final String ACCOUNT_SID = "AC512c8402fb24b07bd960df548dbfaa44";
    public static final String AUTH_TOKEN =  "3c50f305d8f96bee849cc5022679c186";

    public void sendSms(String body) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(new PhoneNumber("+21629288025"),
        new PhoneNumber("+18647271456"), 
        body).create();
    }   
}
