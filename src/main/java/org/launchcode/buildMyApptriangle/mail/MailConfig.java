package org.launchcode.buildMyApptriangle.mail;


import org.simplejavamail.email.EmailBuilder;

public class MailConfig {

    private static Email buildEmail(String from, String to, String subject, String html, String plainText) {
        return (Email) EmailBuilder.startingBlank()
                .from("XMPP Compliance Tester", from)
                .to(to)
                .withSubject(subject)
                .withHTMLText(html)
                .withPlainText(plainText)
                .buildEmail();
    }
}