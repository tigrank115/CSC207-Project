package use_case.login;

import entity.User;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String emailAddress = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByEmail(emailAddress)) {
            loginPresenter.prepareFailView(emailAddress + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.get(emailAddress).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + emailAddress + "\".");
            }
            else {

                final User user = userDataAccessObject.get(loginInputData.getUsername());
                userDataAccessObject.setCurrentUser(user.getEmailAddress());

                final LoginOutputData loginOutputData = new LoginOutputData(user.getEmailAddress(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void switchToSignupView() {
        loginPresenter.switchToSignupView();
    }
}
