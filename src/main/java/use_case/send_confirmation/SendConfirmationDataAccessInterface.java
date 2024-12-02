package use_case.send_confirmation;

import entity.User;

public interface SendConfirmationDataAccessInterface {
    boolean sendConfirmationEmail(User recipient, String body);
}
