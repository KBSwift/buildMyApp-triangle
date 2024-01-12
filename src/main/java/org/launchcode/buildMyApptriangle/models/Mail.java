package org.launchcode.buildMyApptriangle.models;

import com.sun.jdi.connect.Transport;
import org.springframework.context.NoSuchMessageException;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.nio.channels.UnresolvedAddressException;
import java.util.Properties;

public class Mail {

  Session newSession =null;
    MimeMessage mimeMessage=null;
    public static void main(String args[]) throws UnresolvedAddressException, NoSuchMessageException {
        Mail mail = new Mail();
        mail.setupServerProperties();
        mail.draftEmail();
        mail.sendEmail();
    }

    private void sendEmail() throws NoSuchMessageException{
        String fromUser;
        String fromUserPassword;
        String emailHost;
        Transport transport= newSession.getTransport("smtp");
        transport.connect(emailHost,fromUser,fromUserPassword);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email Sent.");
    }

    private MimeMessage draftEmail() throws UnresolvedAddressException, NoSuchMessageException{
        String[] emailRecipients={};
        String emailSubject;
        String emailBody;
        mimeMessage =new MimeMessage(newSession);

        for(int i=0; i<emailRecipients.length;i++)
            mimeMessage.addrecipient(Message.RecipientType.TO, new InternetAddress(emailRecipients[i]));
        mimeMessage.setSubject(emailSubject);


        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent( emailBody,"html/text");
        MimeMultipart multipart = new MimeMultipart();
        mimeMessage.setContent(multipart);
        return mimeMessage;
    }

    private void setupServerProperties() {
        //set up amil API for smtp settings

        Properties properties=System.getProperties();
        properties.put("","");
        properties.put("","");
        properties.put("","");
        properties.put("","");
        newSession =Session.getDefaultInstance(properties,null);
    }

}
