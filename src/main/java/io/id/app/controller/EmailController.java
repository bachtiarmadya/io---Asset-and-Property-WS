/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.configuration.ApplicationConfiguration;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author permadi
 */
public class EmailController extends BaseController {

    public EmailController() {
        log = getLogger(this.getClass());
    }

    public String getSubject() {
        String subject = getConfig(ApplicationConfiguration.EMAIL_SUBJECT);
        return subject;
    }

    public String getBody(String email, String token) {
        String css = getConfig(ApplicationConfiguration.EMAIL_CSS);
        String link = getConfig(ApplicationConfiguration.EMAIL_LINK);
        String javascript = getConfig(ApplicationConfiguration.EMAIL_JS);
        String template = getConfig(ApplicationConfiguration.EMAIL_TEMPLATE);
        String result = template
                .replace("[style_css]", css)
                .replace("[javasript]", javascript)
                .replace("[name]", email)
                .replace("[generated_link]", link + token);
        return result;
    }
    
    

    public boolean sendEmail(String email, String subject, String body) {
        boolean isSend = false;
        //getConfig(ApplicationConfiguration.EMAIL_SUBJECT);
        final String username = getConfig(ApplicationConfiguration.MAIL_SMTP_USERNAME);;
        final String password = getConfig(ApplicationConfiguration.MAIL_SMTP_PASSWORD);;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", getConfig(ApplicationConfiguration.MAIL_SMTP_HOST));
        prop.put("mail.smtp.port", getConfig(ApplicationConfiguration.MAIL_SMTP_PORT));
        prop.put("mail.smtp.auth", getConfig(ApplicationConfiguration.MAIL_SMTP_AUTH));
        prop.put("mail.smtp.starttls.enable", getConfig(ApplicationConfiguration.MAIL_SMTP_STARTTTLS)); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getConfig(ApplicationConfiguration.MAIL_SMTP_ADDRESS)));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(getConfig(ApplicationConfiguration.EMAIL_SUBJECT));
           
            String emailBody = body;
            message.setText(emailBody);

            Transport.send(message);

            isSend = true;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return isSend;
    }

}
