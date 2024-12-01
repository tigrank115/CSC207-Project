package interface_adapter.respond;

import use_case.get_survey.GetSurveyInputBoundary;
import use_case.get_survey.GetSurveyInputData;
import use_case.login.LoginInputData;
import use_case.make_response.MakeResponseInputBoundary;
import use_case.make_response.MakeResponseInputData;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Respond to a survey Use Case.
 */
public class RespondToASurveyController {

    private final GetSurveyInputBoundary userGetSurveyUseCaseInteractor;

    public RespondToASurveyController(GetSurveyInputBoundary userGetSurveyUseCaseInteractor) {
        this.userGetSurveyUseCaseInteractor = userGetSurveyUseCaseInteractor;
    }

    /**
     * Executes the Respond to a survey Use Case.
     */
    public void execute(String username, String surveyID) {
        final GetSurveyInputData getSurveyInputData = new GetSurveyInputData(
                username, surveyID);


        userGetSurveyUseCaseInteractor.switchToResponseView(getSurveyInputData, username);

    }

}
