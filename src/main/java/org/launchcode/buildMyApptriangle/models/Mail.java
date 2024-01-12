package org.launchcode.buildMyApptriangle.models;

import org.springframework.context.NoSuchMessageException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.nio.channels.UnresolvedAddressException;
import java.util.Properties;

public class Mail {

  Session newSession =null;
    MimeMessage mimeMessage=null;
    public static void main(String args[]) throws UnresolvedAddressException, NoSuchMessageException, MessagingException {
        Mail mail = new Mail();
        mail.setupServerProperties();
        mail.draftEmail();
        mail.sendEmail();
    }

    private void sendEmail() throws NoSuchMessageException, MessagingException {
        String fromUser="test.gmail";
        String fromUserPassword="********";
        String emailHost="smtp.gmail.com";
        Transport transport= newSession.getTransport("smtp");
        transport.connect(emailHost,fromUser,fromUserPassword);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email Sent.");
    }

    @SuppressWarnings("SuspiciousIndentAfterControlStatement")
    private MimeMessage draftEmail() throws UnresolvedAddressException, NoSuchMessageException, MessagingException {
        String[] emailRecipients={"abc@gmail.com","xyz@gmail.com"};
        String emailSubject="test mail";
        String emailBody="test body of email";
        mimeMessage =new MimeMessage(newSession);

        for(int i=0; i<emailRecipients.length;i++)
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipients[i]));
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
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        newSession =Session.getDefaultInstance(properties,null);
    }

}
