package com.booknet.system.mail.model;

import java.util.ArrayList;
import java.util.List;

public class TextEmail {
    List<String> recipients = new ArrayList<>();
    String subject;
    String content;

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public void addRecipient(String newRecipient) {
        if (!recipients.contains(newRecipient)) {
            recipients.add(newRecipient);
        }
    }

    public void removeRecipient(String removeRecipient) {
        recipients.remove(removeRecipient);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}