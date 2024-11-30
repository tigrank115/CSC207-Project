package interface_adapter.ResetPassword;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

/**
 * The View Model for the Reset Password View.
 */
public class ResetPasswordViewModel extends ViewModel<ResetPasswordState> {

    public ResetPasswordViewModel() {
        super("reset password");
        setState(new ResetPasswordState());
    }
}
