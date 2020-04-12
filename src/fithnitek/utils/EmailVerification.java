/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.utils;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author marwe
 */
public class EmailVerification {
    
    public void sendMail(String code,String receiver)
    { 
        String from = "fithnitek.pidev@gmail.com"; 
        String host = "smtp.gmail.com";
  
        //Get the session object  
        Properties properties = System.getProperties();  
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(properties,new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                "fithnitek.pidev@gmail.com", "fhX6StE3yHYswrX");
                    }
        });  
                
        try{  
            MimeMessage message = new MimeMessage(session);  
            message.setFrom(new InternetAddress(from));  
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(receiver));  
            message.setSubject("Verification Code");  
            message.setText("Hello, this is your verification code:  "+ code);  
 
            Transport.send(message);  
            System.out.println("message sent successfully....");  

        }
         catch (MessagingException mex) {
             mex.printStackTrace();
        }    
    }
    
    
    
    public String generateCode()
    {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }
    
}
