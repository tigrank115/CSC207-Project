package use_case.signup;

import entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface SignupUserDataAccessInterface {

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
}
