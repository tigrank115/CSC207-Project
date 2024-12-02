package data_access.api;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.*;
import com.sendgrid.helpers.mail.objects.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

public class SendGridClient implements EmailSender {

    public static final int OK_STATUS = 200;


    private final String address;

    public SendGridClient(String address, String apiKey) {
        this.address = address;
    }

    private void trySendEmail(Mail mail) {
        SendGrid sendGrid = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);

            if (response.getStatusCode() != OK_STATUS) {
                throw new RuntimeException(String.format("Error sending email (%d): %s",
                        response.getStatusCode(),
                        response.getBody()));
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendEmail(String recipient, String subject, String body) {

        Email from = new Email(address);
        Email to = new Email(recipient);
        Content content = new Content("text/plain", body);


        Mail mail = new Mail(from, subject, to, content);

        trySendEmail(mail);
    }

    @Override
    public void sendEmailJSON(String recipient, String subject, String body, JSONObject object) {
        Email from = new Email(address);
        Email to = new Email(recipient);
        Content content = new Content("text/plain", body);

        String encodedJson = Base64.getEncoder().encodeToString(object.toString().getBytes());

        Mail mail = new Mail(from, subject, to, content);

        // Create the attachment
        Attachments attachment = new Attachments();
        attachment.setContent(encodedJson);
        attachment.setType("application/json");
        attachment.setFilename("response_data.json");
        attachment.setDisposition("attachment");

        mail.addAttachments(attachment);

        trySendEmail(mail);
    }
}
