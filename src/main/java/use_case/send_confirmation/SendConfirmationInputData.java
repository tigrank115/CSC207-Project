package use_case.send_confirmation;

import entity.Survey;
import entity.User;

public class SendConfirmationInputData {
    private final Survey survey;
    private final User user;

    public SendConfirmationInputData(Survey survey, User user) {
        this.survey = survey;
        this.user = user;
    }

    public Survey getSurvey() {
        return survey;
    }

    public User getUser() {
        return user;
    }
}
