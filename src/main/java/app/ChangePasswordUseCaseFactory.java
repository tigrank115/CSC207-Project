package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ResetPassword.ResetPasswordViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.ChangePasswordController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import view.LoggedInView;
import view.ResetPasswordView;

/**
 * This class contains the static factory function for creating the LoggedInView.
 */
public final class ChangePasswordUseCaseFactory {

    /** Prevent instantiation. */
    private ChangePasswordUseCaseFactory() {

    }

    /**
     * Factory function for creating the LoggedInView.
     * @param viewManagerModel the ViewManagerModel to inject into the LoggedInView
     * @param loggedInViewModel the loggedInViewModel to inject into the LoggedInView
     * @param userDataAccessObject the ChangePasswordUserDataAccessInterface to inject into the LoggedInView
     * @return the LoggedInView created for the provided input classes
     */
    public static LoggedInView create(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            ResetPasswordViewModel resetPasswordViewModel,
            ChangePasswordUserDataAccessInterface resetUserDataAccessObject,
            LoginUserDataAccessInterface userDataAccessObject) {

        final ChangePasswordController changePasswordController =
                    createChangePasswordUseCase(viewManagerModel, loggedInViewModel, resetPasswordViewModel,
                            userDataAccessObject, resetUserDataAccessObject);
        return new LoggedInView(loggedInViewModel, changePasswordController);

    }

    private static ChangePasswordController createChangePasswordUseCase(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            ResetPasswordViewModel resetPasswordViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            ChangePasswordUserDataAccessInterface resetUserDataAccessObject) {

        // Notice how we pass this method's parameters through to the Presenter.
        final ChangePasswordOutputBoundary changePasswordOutputBoundary = new LoggedInPresenter(viewManagerModel,
                loggedInViewModel, resetPasswordViewModel);

        final UserFactory userFactory = new CommonUserFactory();

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, resetUserDataAccessObject,
                        changePasswordOutputBoundary, userFactory);

        return new ChangePasswordController(changePasswordInteractor);
    }
}
