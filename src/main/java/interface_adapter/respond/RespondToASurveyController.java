package interface_adapter.respond;

import use_case.make_response.MakeResponseInputBoundary;
import use_case.make_response.MakeResponseInputData;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Respond to a survey Use Case.
 */
public class RespondToASurveyController {

    private final MakeResponseInputBoundary userMakeResponseUseCaseInteractor;

    public RespondToASurveyController(MakeResponseInputBoundary userMakeResponseUseCaseInteractor) {
        this.userMakeResponseUseCaseInteractor = userMakeResponseUseCaseInteractor;
    }

    /**
     * Executes the Respond to a survey Use Case.
     */
    public void execute(String username, String password1, String password2) {
        final SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

}
