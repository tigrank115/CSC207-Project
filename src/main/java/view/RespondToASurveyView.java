package view;

import interface_adapter.respond.RespondToASurveyController;
import interface_adapter.respond.RespondToASurveyState;
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
        private final String viewName = "respond to a survey";

        private final RespondToASurveyViewModel respondtoasurveyViewModel;
        private final JTextField idInputField = new JTextField(20);
        private final RespondToASurveyController respondtoasurveyController;
        private final JLabel surveyErrorField = new JLabel();

        private final JButton ok;
        private final JButton cancel;

        public RespondToASurveyView(RespondToASurveyController controller, RespondToASurveyViewModel respondtoasurveyViewModel) {

                this.respondtoasurveyController = controller;
                this.respondtoasurveyViewModel = respondtoasurveyViewModel;
                respondtoasurveyViewModel.addPropertyChangeListener(this);

                final JLabel title = new JLabel(RespondToASurveyViewModel.TITLE_LABEL);
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
                                                final RespondToASurveyState currentState = respondtoasurveyViewModel.getState();

                                                respondtoasurveyController.execute(currentState.getUsername(),
                                                        currentState.getSurveyID()
                                                );
                                        }
                                }
                        }
                );

                cancel.addActionListener(this);
                addIDListener();


                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                this.add(title);
                this.add(idInfo);
                this.add(surveyErrorField);
                this.add(buttons);

        }
        private void addIDListener() {
                idInputField.getDocument().addDocumentListener(new DocumentListener() {

                        private void documentListenerHelper() {
                                final RespondToASurveyState currentState = respondtoasurveyViewModel.getState();
                                currentState.setSurveyID(idInputField.getText());
                                respondtoasurveyViewModel.setState(currentState);
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
                final RespondToASurveyState state = (RespondToASurveyState) evt.getNewValue();
                surveyErrorField.setText(state.getSurveyIDError());
        }

        public String getViewName() {
                return viewName;
        }
}
