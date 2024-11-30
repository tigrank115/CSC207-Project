package interface_adapter.logged_in;

import interface_adapter.ResetPassword.ResetPasswordState;
import interface_adapter.ResetPassword.ResetPasswordViewModel;
import interface_adapter.ViewManagerModel;
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

    public LoggedInPresenter(ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel, ResetPasswordViewModel resetPasswordViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.resetPasswordViewModel = resetPasswordViewModel;
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
}
