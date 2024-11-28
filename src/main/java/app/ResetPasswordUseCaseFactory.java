package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ResetPassword.ResetPasswordPresenter;
import interface_adapter.ResetPassword.ResetPasswordViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.ChangePasswordController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import view.LoggedInView;
import view.ResetPasswordView;

/**
 * This class contains the static factory function for creating the LoggedInView.
 */
public final class ResetPasswordUseCaseFactory {

    /** Prevent instantiation. */
    private ResetPasswordUseCaseFactory() {

    }

    /**
     * Factory function for creating the LoggedInView.
     * @param viewManagerModel the ViewManagerModel to inject into the ResetPasswordView
     * @param resetPasswordViewModel the resetPasswordViewModel to inject into the ResetPasswordView
     * @param userDataAccessObject the ChangePasswordUserDataAccessInterface to inject into the ResetPasswordView
     * @return the LoggedInView created for the provided input classes
     */
    public static ResetPasswordView create(
            ViewManagerModel viewManagerModel,
            ResetPasswordViewModel resetPasswordViewModel,
            LoggedInViewModel loggedInViewModel,
            ChangePasswordUserDataAccessInterface userDataAccessObject) {

        final ChangePasswordController changePasswordController =
                createResetPasswordUseCase(viewManagerModel, resetPasswordViewModel,
                        loggedInViewModel, userDataAccessObject);
        return new ResetPasswordView(resetPasswordViewModel, changePasswordController);

    }

    private static ChangePasswordController createResetPasswordUseCase(
            ViewManagerModel viewManagerModel,
            ResetPasswordViewModel resetPasswordViewModel,
            LoggedInViewModel loggedInViewModel,
            ChangePasswordUserDataAccessInterface userDataAccessObject) {

        // Notice how we pass this method's parameters through to the Presenter.
        final ChangePasswordOutputBoundary changePasswordOutputBoundary = new ResetPasswordPresenter(viewManagerModel,
                resetPasswordViewModel, loggedInViewModel);

        final UserFactory userFactory = new CommonUserFactory();

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        return new ChangePasswordController(changePasswordInteractor);
    }
}

