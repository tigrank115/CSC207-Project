package entity;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the email address of the user.
     * @return the email address of the user.
     */
    String getEmailAddress();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

}
