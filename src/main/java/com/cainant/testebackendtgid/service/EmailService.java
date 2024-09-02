package com.cainant.testebackendtgid.service;

import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.exceptions.MailerSendException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${mailersend.api.token}")
    private String apiToken;

    @Value("${mailersend.api.email}")
    private String emailMailerSend;

    public void notifyUser(String name, String emailAddr, String payload) {
        var email = new Email();

        email.setFrom("tgid", emailMailerSend);
        email.addRecipient(name, emailAddr);
        email.setSubject("Transaction information");
        email.setPlain(payload);

        var ms = new MailerSend();
        ms.setToken(apiToken);

        try {
            ms.emails().send(email);
        } catch (MailerSendException e) {
            throw new RuntimeException(e);
        }
    }
}
