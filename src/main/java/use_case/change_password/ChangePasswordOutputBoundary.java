package use_case.change_password;

import use_case.login.LoginInputData;
import use_case.login.LoginOutputData;

/**
 * The output boundary for the Change Password Use Case.
 */
public interface ChangePasswordOutputBoundary {
    /**
     * Prepares the success view for the Change Password Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ChangePasswordOutputData outputData);

    /**
     * Prepares the failure view for the Change Password Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    void switchToLoggedInView();

    void switchToResetPasswordView(LoginOutputData loginOutputData);

    void switchToLoginView();

    void switchToRespondSurveyView(LoginOutputData loginOutputData);

    void switchToCreateSurveyView(LoginOutputData loginOutputData);
}
