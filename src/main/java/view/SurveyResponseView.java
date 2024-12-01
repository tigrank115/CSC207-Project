package view;

import entity.AnswerType;
import entity.Question;
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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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


    public SurveyResponseView(SurveyResponseController controller, SurveyResponseViewModel respondtoasurveyViewModel) {

        this.surveyResponseController = controller;
        this.surveyResponseViewModel = respondtoasurveyViewModel;
        this.surveyResponseViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Survey Response View");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel titleInfo = new JLabel("Title of Survey:");
        titleInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        surveyTitle = new JLabel();

        questionPrompts = new ArrayList<>();
        textAnswerFields = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            questionPrompts.add(new JLabel());
            JTextField textField = new JTextField(20);
            textField.setVisible(false);
            textAnswerFields.add(textField);
        }

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(titleInfo);
        this.add(surveyTitle);
        for (int i = 0; i < questionPrompts.size(); i++) {
            this.add(questionPrompts.get(i));
            this.add(textAnswerFields.get(i));

        }

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
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
            if (state.getSurvey().getQuestions().get(i).getAnswerType().equals(AnswerType.TEXT)) {
                textAnswerFields.get(i).setVisible(true);
            }
            else if (state.getSurvey().getQuestions().get(i).getAnswerType().equals(AnswerType.MULTIPLE_CHOICE)) {

            }
        }
    }

    public String getViewName() {
        return viewName;
    }
}
