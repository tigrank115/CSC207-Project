package use_case.send_confirmation;

import entity.User;

public interface SendConfirmationDataAccessInterface {
    void sendConfirmationEmail(User recipient, String surveyName, String body);
}
