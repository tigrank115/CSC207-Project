package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String emailAddress, String password) {
        return new CommonUser(emailAddress, password);
    }
}
