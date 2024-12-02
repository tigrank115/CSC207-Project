package interface_adapter.surveyresponse;

import entity.Survey;
import entity.User;
import use_case.make_response.MakeResponseInputBoundary;
import use_case.make_response.MakeResponseInputData;
import use_case.make_response.MakeResponseOutputBoundary;
import use_case.send_confirmation.SendConfirmationInputBoundary;
import use_case.send_confirmation.SendConfirmationInputData;

public class SurveyResponseController {
    MakeResponseInputBoundary makeResponseInteractor;
    SendConfirmationInputBoundary sendConfirmationInteractor;

    public SurveyResponseController(MakeResponseInputBoundary makeResponseInteractor,
                                    SendConfirmationInputBoundary sendConfirmationInteractor) {
        this.makeResponseInteractor = makeResponseInteractor;
        this.sendConfirmationInteractor = sendConfirmationInteractor;
    }

    // Submitting Responses not implemented on this layer, due to lack of SurveyResponseState thoroughness
    public void makeResponse(Survey survey, User user) {
        sendConfirmationInteractor.execute(new SendConfirmationInputData(survey, user));
    }
}
