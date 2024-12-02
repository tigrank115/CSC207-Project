package data_access.api;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.json.JSONObject;

import java.util.Base64;

public class MailBuilder {

    private static final String FROM = "quik.survey2024@gmail.com";

    private String from = FROM;
    private String recipient = FROM;
    private String subject = "<No Subject>";
    private String body = "<No Body>";
    private JSONObject jsonAttachment = new JSONObject();

    public MailBuilder addRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public MailBuilder addSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailBuilder addBody(String body) {
        this.body = body;
        return this;
    }

    public MailBuilder addJsonAttachment(JSONObject attachment) {
        this.jsonAttachment = attachment;
        return this;
    }

    public Mail build() {
        Content content = new Content("text/plain", body);

        Mail mail = new Mail(new Email(from), subject, new Email(recipient), content);

        String encodedJson = Base64.getEncoder().encodeToString(jsonAttachment.toString().getBytes());

        // Create the attachment
        Attachments attachment = new Attachments();
        attachment.setContent(encodedJson);
        attachment.setType("application/json");
        attachment.setFilename("response_data.json");
        attachment.setDisposition("attachment");

        mail.addAttachments(attachment);

        return mail;
    }
}
