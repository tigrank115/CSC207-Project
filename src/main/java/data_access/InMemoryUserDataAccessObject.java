package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
                                                     LoginUserDataAccessInterface,
                                                     ChangePasswordUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUser;

    @Override
    public boolean existsByEmail(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getEmailAddress(), user);
    }

    @Override
    public User get(String emailAddress) {
        return users.get(emailAddress);
    }

    @Override
    public void setCurrentUser(String name) {
        this.currentUser = name;
    }

    @Override
    public String getCurrentUser() {
        return currentUser;
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getEmailAddress(), user);
    }

}
