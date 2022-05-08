package com.booknet.system.mail;

import com.booknet.system.mail.model.TextEmail;
import com.booknet.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    private static Session getNewSession() {
        var props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", MailConfig.HOST_NAME);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", MailConfig.TSL_PORT);

        return Session.getDefaultInstance(
                props
                , new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
                    }
                }
        );
    }

    public static void sendTextMail(TextEmail email) {
        var session = getNewSession();
        try {
            var listRecipients = email.getRecipients();
            var subject = email.getSubject();
            var content = email.getContent();

            var message = new MimeMessage(session);
            for (var recipient : listRecipients) {
                message.addRecipients(
                        Message.RecipientType.TO
                        , InternetAddress.parse(recipient)
                );
            }
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);

            logger.info("Email send successfully to {}", Utils.json.stringify(listRecipients));
        } catch (MessagingException e) {
            logger.error("Email send failed");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}