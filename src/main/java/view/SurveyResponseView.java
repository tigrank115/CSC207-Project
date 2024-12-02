package view;

import entity.AnswerType;
import entity.CommonUserFactory;
import entity.Question;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.respond.RespondToASurveyController;
import interface_adapter.respond.RespondToASurveyState;
import interface_adapter.respond.RespondToASurveyViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.surveyresponse.SurveyResponseController;
import interface_adapter.surveyresponse.SurveyResponseState;
import interface_adapter.surveyresponse.SurveyResponseViewModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The View for the "Respond to a survey" case.
 */
public class SurveyResponseView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Survey Response View";
    private final SurveyResponseViewModel surveyResponseViewModel;
    private final SurveyResponseController surveyResponseController;
    private final JLabel surveyTitle;
    private final List<JLabel> questionPrompts;
    private final List<JTextField> textAnswerFields;
    private final List<List<JRadioButton>> multipleChoiceButtons;
    private final JButton submit;
    private final LoggedInViewModel loggedInViewModel;


    public SurveyResponseView(SurveyResponseController controller,
                              SurveyResponseViewModel respondtoasurveyViewModel,
                              LoggedInViewModel loggedinViewModel) {

        this.surveyResponseController = controller;
        this.surveyResponseViewModel = respondtoasurveyViewModel;
        this.loggedInViewModel = loggedinViewModel;
        this.surveyResponseViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Survey Response View");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel titleInfo = new JLabel("Title of Survey:");
        titleInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        surveyTitle = new JLabel();

        submit = new JButton("Submit Response");
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);

        submit.addActionListener(this);

        questionPrompts = new ArrayList<>();
        textAnswerFields = new ArrayList<>();
        multipleChoiceButtons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            questionPrompts.add(new JLabel());
            JTextField textField = new JTextField(20);
            textField.setVisible(false);
            textAnswerFields.add(textField);

            List<JRadioButton> buttonGrouping = new ArrayList();
            for (int j = 0; j < 4; j++) {
                JRadioButton radioButton = new JRadioButton();
                radioButton.setVisible(false);
                buttonGrouping.add(radioButton);
            }
            multipleChoiceButtons.add(buttonGrouping);
        }

//        submit.addActionListener(
//                // This creates an anonymous subclass of ActionListener and instantiates it.
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(submit)) {
//                            final SurveyResponseState currentState = surveyResponseViewModel.getState();
//
//                            surveyResponseController.execute(currentState.getUsername(),
//                                    currentState.getSurveyID()
//                            );
//                        }
//                    }
//                }
//        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(titleInfo);
        this.add(surveyTitle);
        for (int i = 0; i < questionPrompts.size(); i++) {
            this.add(questionPrompts.get(i));
            this.add(textAnswerFields.get(i));
            for (JRadioButton radioButton : multipleChoiceButtons.get(i)) {
                this.add(radioButton);
            }

        }
        this.add(submit);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        surveyResponseController.makeResponse(
                surveyResponseViewModel.getState().getSurvey(),
                (new CommonUserFactory()).create(
                        surveyResponseViewModel.getState().getEmailAddress(),
                        loggedInViewModel.getState().getPassword()));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SurveyResponseState state = (SurveyResponseState) evt.getNewValue();
        surveyTitle.setText(state.getSurvey().getName());
        surveyTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        for (int i = 0; i < state.getSurvey().getQuestions().size(); i++) {
            questionPrompts.get(i).setText("Question " + i + ": " + state.getSurvey().getQuestions().get(i).getPrompt() +
                    " (required question: " +
                    state.getSurvey().getQuestions().get(i).isRequired() + ")");
            questionPrompts.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);

            AnswerType answerType = state.getSurvey().getQuestions().get(i).getAnswerType();
            if (answerType.equals(AnswerType.TEXT)) {
                textAnswerFields.get(i).setVisible(true);
            } else {
                for (int j = 0; j < state.getSurvey().getQuestions().get(i).getOptions().size(); j++) {
                    multipleChoiceButtons.get(i).get(j).setVisible(true);
                    multipleChoiceButtons.get(i).get(j).setAlignmentX(Component.CENTER_ALIGNMENT);
                    multipleChoiceButtons.get(i).get(j).setText(state.getSurvey().getQuestions().get(i).getOptions().get(j));
                }
            }
        }
    }

    public String getViewName() {
        return viewName;
    }
}
