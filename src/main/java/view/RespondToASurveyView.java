package view;

import interface_adapter.respond.RespondToASurveyController;
import interface_adapter.respond.RespondToASurveyViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

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

/**
 * The View for the "Respond to a survey" case.
 */
public class RespondToASurveyView extends JPanel implements ActionListener, PropertyChangeListener {
        private final String viewName = "responding to a survey";

        private final RespondToASurveyViewModel respondtoasurveyViewModel;
        private final JTextField idInputField = new JTextField(15);
        private final RespondToASurveyController respondtoasurveyController;

        private final JButton ok;
        private final JButton cancel;

        public RespondToASurveyView(RespondToASurveyController controller, RespondToASurveyViewModel respondtoasurveyViewModel) {

                this.respondtoasurveyController = controller;
                this.respondtoasurveyViewModel = respondtoasurveyViewModel;
                respondtoasurveyViewModel.addPropertyChangeListener(this);

                final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
                title.setAlignmentX(Component.CENTER_ALIGNMENT);

                final LabelTextPanel idInfo = new LabelTextPanel(
                        new JLabel(RespondToASurveyViewModel.ID_LABEL), idInputField);

                final JPanel buttons = new JPanel();
                ok = new JButton(RespondToASurveyViewModel.OK_BUTTON_LABEL);
                buttons.add(ok);
                cancel = new JButton(RespondToASurveyViewModel.CANCEL_BUTTON_LABEL);
                buttons.add(cancel);

                ok.addActionListener(
                        // This creates an anonymous subclass of ActionListener and instantiates it.
                        new ActionListener() {
                                public void actionPerformed(ActionEvent evt) {
                                        if (evt.getSource().equals(ok)) {
                                                final SignupState currentState = signupViewModel.getState();

                                                signupController.execute(
                                                        currentState.getUsername(),
                                                        currentState.getPassword(),
                                                        currentState.getRepeatPassword()
                                                );
                                        }
                                }
                        }
                );

                cancel.addActionListener(this);

                addUsernameListener();
                addPasswordListener();
                addRepeatPasswordListener();

                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

                this.add(title);
                this.add(idInfo);
                this.add(buttons);


        }
        private void addIDListener() {
                idInputField.getDocument().addDocumentListener(new DocumentListener() {

                        private void documentListenerHelper() {
                                final SignupState currentState = signupViewModel.getState();
                                currentState.setUsername(usernameInputField.getText());
                                signupViewModel.setState(currentState);
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
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
                final SignupState state = (SignupState) evt.getNewValue();
                if (state.getUsernameError() != null) {
                        JOptionPane.showMessageDialog(this, state.getUsernameError());
                }
        }

        public String getViewName() {
                return viewName;
        }
}
