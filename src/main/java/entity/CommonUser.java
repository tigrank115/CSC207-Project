package entity;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String emailAddress;
    private final String password;

    public CommonUser(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
