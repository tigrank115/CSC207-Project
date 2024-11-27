package interface_adapter.get_all_surveys;

import use_case.signup.SignupInputBoundary;

public class GetMySurveysController {

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    private final SignupInputBoundary userSignupUseCaseInteractor;

    public GetMySurveysController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void switchToLoginView() {
        userSignupUseCaseInteractor.switchToLoginView();
    }
}
