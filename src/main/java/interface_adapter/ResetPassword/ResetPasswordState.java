package interface_adapter.ResetPassword;

/**
 * The state for the Reset Password View Model.
 */
public class ResetPasswordState {
    private String username = "";

    private String password = "";
    private String passwordError;

    public ResetPasswordState(ResetPasswordState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ResetPasswordState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return password;
    }
}
