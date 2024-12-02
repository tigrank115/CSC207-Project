package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.logged_in.ChangePasswordController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private final ChangePasswordController changePasswordController;

    private final JLabel username;

    private final JButton logOut;

    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;
    private final JButton respondSurvey;
    private final JButton createSurvey;

    public LoggedInView(LoggedInViewModel loggedInViewModel, ChangePasswordController changePasswordController) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.changePasswordController = changePasswordController;

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        respondSurvey = new JButton("Respond to a Survey");
        buttons.add(respondSurvey);

        createSurvey = new JButton("Create a Survey");
        buttons.add(createSurvey);

        logOut.addActionListener(                new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                changePasswordController.switchToLoginView();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        changePassword.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(changePassword)) {
                            final LoggedInState currentState = loggedInViewModel.getState();

                            changePasswordController.switchToResetPasswordView(currentState.getPassword(),
                                    currentState.getUsername());
                        }
                    }
                }
        );

        respondSurvey.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(respondSurvey)) {
                            final LoggedInState currentState = loggedInViewModel.getState();

                            changePasswordController.switchToRespondSurveyView(currentState.getPassword(),
                                    currentState.getUsername());
                        }
                    }
                }
        );

        createSurvey.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createSurvey)) {
                            final LoggedInState currentState = loggedInViewModel.getState();

                            changePasswordController.switchToCreateSurveyView(currentState.getPassword(),
                                    currentState.getUsername());
                        }
                    }
                }
        );

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }
}
