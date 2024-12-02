package use_case.change_password;

import entity.User;
import entity.UserFactory;
import interface_adapter.logged_in.LoggedInPresenter;
import use_case.login.LoginInputData;
import use_case.login.LoginOutputData;
import use_case.login.LoginUserDataAccessInterface;

/**
 * The Change Password Interactor.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final ChangePasswordUserDataAccessInterface resetUserDataAccessObject;
    private final ChangePasswordOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public ChangePasswordInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                                    ChangePasswordUserDataAccessInterface changePasswordDataAccessInterface,
                                    ChangePasswordOutputBoundary changePasswordOutputBoundary,
                                    UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.resetUserDataAccessObject = changePasswordDataAccessInterface;
        this.userPresenter = changePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        final User user = userFactory.create(changePasswordInputData.getUsername(),
                                             changePasswordInputData.getPassword());
        resetUserDataAccessObject.changePassword(user);

        final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                                                                                  false);
        userPresenter.prepareSuccessView(changePasswordOutputData);
    }

    @Override
    public void switchToLoggedInView() {
        userPresenter.switchToLoggedInView();
    }

    @Override
    public void switchToResetPasswordView(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        final String pwd = userDataAccessObject.get(username).getPassword();

        final User user = userDataAccessObject.get(loginInputData.getUsername());
        userDataAccessObject.setCurrentUser(user.getName());

        final LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
        userPresenter.switchToResetPasswordView(loginOutputData);
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }

    @Override
    public void switchToRespondSurveyView(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        final String pwd = userDataAccessObject.get(username).getPassword();

        final User user = userDataAccessObject.get(loginInputData.getUsername());
        userDataAccessObject.setCurrentUser(user.getName());

        final LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
        userPresenter.switchToRespondSurveyView(loginOutputData);
    }
}
