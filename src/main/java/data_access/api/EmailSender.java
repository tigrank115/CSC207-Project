package data_access.api;

import org.json.JSONObject;

public interface EmailSender {
    void sendEmail(String recipient, String subject, String body);

    void sendEmailJSON(String recipient, String subject, String body, JSONObject object);
}
