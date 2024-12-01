package view;

import interface_adapter.create_survey.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateSurveyView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "create survey";
    private final CreateSurveyViewModel createSurveyViewModel;


    private final JTextField surveyTitleField;
    private final JButton newQuestionButton;
    private final CreateSurveyController createSurveyController;

    private final JPanel questionPanels;
    private final JScrollPane questionScroller;


    public CreateSurveyView(CreateSurveyViewModel createSurveyViewModel,
                            CreateSurveyController createSurveyController) {

        this.createSurveyController = createSurveyController;
        this.createSurveyViewModel = createSurveyViewModel;
        this.createSurveyViewModel.addPropertyChangeListener(this);



        surveyTitleField = new JTextField(32);
        surveyTitleField.setMaximumSize(new Dimension(400, 30));
        surveyTitleField.setAlignmentX(Component.CENTER_ALIGNMENT);

        surveyTitleField.getDocument().addDocumentListener(
            new DocumentListener() {
                private void documentListenerHelper() {
                    final CreateSurveyState currentState = createSurveyViewModel.getState();
                    currentState.setSurveyName(surveyTitleField.getText());
                    createSurveyViewModel.setState(currentState);
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
            }
        );

        newQuestionButton = new JButton("New Question...");
        newQuestionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newQuestionButton.addActionListener(
                evt -> {
                    String[] questionTypes = new String[]{
                            "TextQuestion",
                            "MultipleChoiceQuestion",
                    };
                    int questionType = JOptionPane.showOptionDialog(null,
                            "Choose a Question type.",
                            "",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            questionTypes,
                            questionTypes[0]);

                    createSurveyController.addQuestion(questionTypes[questionType]);
                }
        );

        questionPanels = new JPanel();
        questionPanels.setLayout(new GridLayout(0, 1, 1,1));

        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.add(questionPanels, BorderLayout.PAGE_START);

        questionScroller = new JScrollPane(outerPanel);
        questionScroller.setPreferredSize(new Dimension(800, 300));
        questionScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.setLayout(new BorderLayout());

        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel, BoxLayout.X_AXIS));
        mainMenuPanel.add(new JLabel("Survey Title:"));
        mainMenuPanel.add(surveyTitleField);
        mainMenuPanel.add(newQuestionButton);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> createSurveyController.switchToMainMenu());
        mainMenuPanel.add(submitButton);
        mainMenuPanel.add(cancelButton);


        this.add(questionScroller, BorderLayout.CENTER);
//        this.add(surveyTitleField, BorderLayout.CENTER);
//        this.add(newQuestionButton, BorderLayout.PAGE_START);
        this.add(mainMenuPanel, BorderLayout.PAGE_START);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        createSurveyController.uploadSurvey(createSurveyViewModel.getState());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        final LoginState state = (LoginState) evt.getNewValue();
//        setFields(state);
//        usernameErrorField.setText(state.getLoginError());
        if (evt.getPropertyName().equals("state")) {
            final CreateSurveyState state = (CreateSurveyState) evt.getNewValue();

            String error = state.getErrorMessage();

            if (error != null) {
                JOptionPane.showConfirmDialog(null,
                        state.getErrorMessage(),
                        "Error creating Survey",
                        JOptionPane.DEFAULT_OPTION);
            }

            resetView(state.getQuestionStates());
        }

    }

    private void resetView(java.util.List<QuestionState> states) {

        questionPanels.removeAll();
        for (int i = 0; i < states.size(); i++) {
            QuestionState qState = states.get(i);
            JPanel editableQuestionPanel = new JPanel();
            editableQuestionPanel.setLayout(new BoxLayout(editableQuestionPanel, BoxLayout.X_AXIS));

            JButton deleteQuestionButton = new JButton("Delete Question");
            JCheckBox toggleRequired = new JCheckBox("Required?");

            int questionIndex = i;
            deleteQuestionButton.addActionListener(actionEvent -> createSurveyController.removeQuestion(questionIndex));

            toggleRequired.setSelected(qState.isRequired());
            toggleRequired.addActionListener(actionEvent ->
                    createSurveyController.setRequired(questionIndex, toggleRequired.isSelected()));

            editableQuestionPanel.add(new JLabel(String.format("Q%d", questionIndex + 1)));
            editableQuestionPanel.add(qState.createQuestionFrame(createSurveyController));
            editableQuestionPanel.add(toggleRequired);
            editableQuestionPanel.add(deleteQuestionButton);

            questionPanels.add(editableQuestionPanel);
        }

        questionPanels.revalidate();
        questionPanels.repaint();
    }

    public String getViewName() {
        return viewName;
    }
}