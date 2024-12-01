package interface_adapter.ResetPassword;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;
import use_case.login.LoginInputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Reset Password Use Case.
 */
public class ResetPasswordPresenter implements ChangePasswordOutputBoundary {

    private final ResetPasswordViewModel resetPasswordViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public ResetPasswordPresenter(ViewManagerModel viewManagerModel, ResetPasswordViewModel resetPasswordViewModel,
                                  LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.resetPasswordViewModel = resetPasswordViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // TODO update the viewmodel!
        resetPasswordViewModel.firePropertyChanged("password");

    }

    @Override
    public void prepareFailView(String error) {
        // TODO update the viewmodel!
    }

    @Override
    public void switchToLoggedInView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
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

    }

    @Override
    public void switchToRespondSurveyView(LoginOutputData loginOutputData) {

    }
}


