package interface_adapter.logged_in;

import interface_adapter.ResetPassword.ResetPasswordState;
import interface_adapter.ResetPassword.ResetPasswordViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.respond.RespondToASurveyState;
import interface_adapter.respond.RespondToASurveyViewModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;
import use_case.login.LoginInputData;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class LoggedInPresenter implements ChangePasswordOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ResetPasswordViewModel resetPasswordViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private RespondToASurveyViewModel respondSurveyViewModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel,
                             ResetPasswordViewModel resetPasswordViewModel, LoginViewModel loginViewModel,
                             RespondToASurveyViewModel respondSurveyViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.resetPasswordViewModel = resetPasswordViewModel;
        this.loginViewModel = loginViewModel;
        this.respondSurveyViewModel = respondSurveyViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // TODO update the viewmodel!
        loggedInViewModel.firePropertyChanged("password");

    }

    @Override
    public void prepareFailView(String error) {
        // TODO update the viewmodel!
    }

    @Override
    public void switchToLoggedInView() {

    }

    @Override
    public void switchToResetPasswordView(LoginOutputData response) {
        final ResetPasswordState resetPasswordState = resetPasswordViewModel.getState();
        resetPasswordState.setUsername(response.getUsername());
        this.resetPasswordViewModel.setState(resetPasswordState);
        this.resetPasswordViewModel.firePropertyChanged();

        this.viewManagerModel.setState(resetPasswordViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void switchToRespondSurveyView(LoginOutputData response) {
        final RespondToASurveyState respondSurveyState = respondSurveyViewModel.getState();
        respondSurveyState.setUsername(response.getUsername());
        this.respondSurveyViewModel.setState(respondSurveyState);
        this.respondSurveyViewModel.firePropertyChanged();

        this.viewManagerModel.setState(respondSurveyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
