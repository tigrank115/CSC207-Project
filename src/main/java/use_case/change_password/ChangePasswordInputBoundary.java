package use_case.change_password;

import use_case.login.LoginInputData;

/**
 * The Change Password Use Case.
 */
public interface ChangePasswordInputBoundary {

    /**
     * Execute the Change Password Use Case.
     * @param changePasswordInputData the input data for this use case
     */
    void execute(ChangePasswordInputData changePasswordInputData);

    void switchToLoggedInView();

    void switchToResetPasswordView(LoginInputData loginInputData);
}
