package interface_adapter.respond;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The Presenter for the Respond to a survey Use Case.
 */
public class RespondToASurveyPresenter {

    private final RespondToASurveyViewModel respondtoasurveyViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

}
