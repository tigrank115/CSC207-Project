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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.see_responses.SeeResponsesViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

    public class SeeResponsesView extends JPanel implements ActionListener, PropertyChangeListener {

        private final String viewName = "see responses";

        private final SeeResponsesViewModel seeresponsesViewModel;
        private final SeeResponsesController seeresponsesController;

        private final JButton exit;

        public SignupView(SignupController controller, SignupViewModel signupViewModel) {

            this.signupController = controller;
            this.signupViewModel = signupViewModel;
            signupViewModel.addPropertyChangeListener(this);

            final JLabel title = new JLabel(SeeResponsesViewModel.TITLE_LABEL);
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            final JPanel buttons = new JPanel();
            exit = new JButton(SeeResponsesViewModel.EXIT_LABEL);
            buttons.add(exit);

            exit.addActionListener(
                    // This creates an anonymous subclass of ActionListener and instantiates it.
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(exit)) {
                                final SeeResponsesState currentState = seeresponsesViewModel.getState();

                                seeresponsesController.execute(
                                        currentState.getUsername(),
                                        currentState.getPassword(),
                                        currentState.getRepeatPassword()
                                );
                            }
                        }
                    }
            );
        }
    }