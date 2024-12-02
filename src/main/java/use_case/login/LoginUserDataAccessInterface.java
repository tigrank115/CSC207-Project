package use_case.login;

import entity.User;

/**
 * DAO for the Login Use Case.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param emailAddress the email address to look for
     * @return true if a user with the given email address exists; false otherwise
     */
    boolean existsByEmail(String emailAddress);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Returns the user with the given username.
     *
     * @param emailAddress the email to look up
     * @return the user with the given email address
     */
    User get(String emailAddress);

    /**
     * Returns the user with the given username.
     * @param name the name
     */
    void setCurrentUser(String name);

    /**
     * Returns the user with the given email address.
     * @return the user with the given email address
     */
    String getCurrentUser();
}
