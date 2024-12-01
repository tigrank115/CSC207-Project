package interface_adapter.logged_in;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;
import use_case.login.LoginInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param password the new password
     * @param username the user whose password to change
     */
    public void execute(String password, String username) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, password);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }

    public void switchToLoggedInView() {
        userChangePasswordUseCaseInteractor.switchToLoggedInView();
    }

    public void switchToResetPasswordView(String password, String username) {
        final LoginInputData loginInputData = new LoginInputData(
                username, password);

        userChangePasswordUseCaseInteractor.switchToResetPasswordView(loginInputData);
    }

    public void switchToLoginView() {
        userChangePasswordUseCaseInteractor.switchToLoginView();
    }

    public void switchToRespondSurveyView(String password, String username) {
        final LoginInputData loginInputData = new LoginInputData(
                username, password);

        userChangePasswordUseCaseInteractor.switchToRespondSurveyView(loginInputData);
    }
}
