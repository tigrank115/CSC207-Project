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

import interface_adapter.ResetPassword.ResetPasswordState;
import interface_adapter.ResetPassword.ResetPasswordViewModel;
import interface_adapter.logged_in.ChangePasswordController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;

/**
 * The View for when the user is logged into the program.
 */
public class ResetPasswordView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "reset password";
    private final ResetPasswordViewModel resetPasswordViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private final ChangePasswordController changePasswordController;

    private final JLabel username;

    private final JButton cancel;

    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;

    public ResetPasswordView(LoggedInViewModel loggedInViewModel,
                             ResetPasswordViewModel resetPasswordViewModel,
                             ChangePasswordController changePasswordController) {
        this.resetPasswordViewModel = resetPasswordViewModel;
        this.resetPasswordViewModel.addPropertyChangeListener(this);
        this.changePasswordController = changePasswordController;

        final JLabel title = new JLabel("Reset Your Password");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("New Password"), passwordInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();
        cancel = new JButton("Back");
        buttons.add(cancel);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePasswordController.switchToLoggedInView();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ResetPasswordState currentState = resetPasswordViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                resetPasswordViewModel.setState(currentState);
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
                            final ResetPasswordState currentState = resetPasswordViewModel.getState();

                            changePasswordController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        this.add(title);
        // this.add(usernameInfo);
        this.add(username);

        this.add(passwordInfo);
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
            final ResetPasswordState state = (ResetPasswordState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final ResetPasswordState state = (ResetPasswordState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }
}
