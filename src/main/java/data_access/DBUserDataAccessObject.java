package data_access;

import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import data_access.database_connection.FirebaseConnectionFactory;
import org.json.JSONObject;

import entity.User;
import entity.UserFactory;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
                                               LoginUserDataAccessInterface,
                                               ChangePasswordUserDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String EMAIL = "emailAddress";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";
    private final UserFactory userFactory;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
    }

    @Override
    public User get(String emailAddress) {
        DocumentReference userRef = FirebaseConnectionFactory.getFirestore()
                .collection("users")
                .document(emailAddress);
        try {
            String password = (String)userRef.get().get().get("password");
            return userFactory.create(emailAddress, password);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setCurrentUser(String emailAddress) {

    }

    @Override
    public String getCurrentUser() {
        return "";
    }

    @Override
    public boolean existsByEmail(String emailAddress) {
        try {
            DocumentReference userRef = FirebaseConnectionFactory.getFirestore()
                    .collection("users")
                    .document(emailAddress);
            return userRef.get().get().exists();
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(String.format("Error trying to get User %s: %s", emailAddress, e.getMessage()));
        }

    }

    @Override
    public void save(User user) {
        DocumentReference newUserRef = FirebaseConnectionFactory.getFirestore()
                .collection("users")
                .document(user.getEmailAddress());

        JSONObject userJson = new JSONObject();
        userJson.put("password", user.getPassword());
        ApiFuture<WriteResult> result = newUserRef.set(userJson.toMap());
    }

    @Override
    public void changePassword(User user) {
        DocumentReference userRef = FirebaseConnectionFactory.getFirestore()
                .collection("users")
                .document(user.getEmailAddress());

        JSONObject userJson = new JSONObject();
        userJson.put("password", user.getPassword());

        ApiFuture<WriteResult> result = userRef.set(userJson.toMap());
    }
}
