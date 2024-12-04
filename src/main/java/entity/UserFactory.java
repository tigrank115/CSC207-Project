package entity;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     *
     * @param emailAddress the email of the new user
     * @param password     the password of the new user
     * @return the new user
     */
    User create(String emailAddress, String password);

}
