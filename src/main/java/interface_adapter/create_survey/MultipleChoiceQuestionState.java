package interface_adapter.create_survey;

import entity.MultipleChoiceQuestion;
import entity.Question;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestionState extends QuestionState {

    private List<String> options;
    private boolean singleAnswer;

    public MultipleChoiceQuestionState() {
        super(MultipleChoiceQuestion.class);
        options = new ArrayList<>();
        options.add("Enter option here");
    }

    public List<String> getOptions() {
        return options;
    }

    public void addOption(String option) {
        options.add(option);
    }

    public void removeOption(int index) {
        options.remove(index);
    }

    private boolean isSingleAnswer() {
        return singleAnswer;
    }

    public void setSingleAnswer(boolean singleAnswer) {
        this.singleAnswer = singleAnswer;
    }

    @Override
    public JPanel createQuestionFrame(CreateSurveyController controller) {
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.X_AXIS));

        JTextField promptInput = new JTextField(getPrompt());
        promptInput.setMaximumSize(new Dimension(800, 50));
        promptInput.setAlignmentY(Component.CENTER_ALIGNMENT);

        promptInput.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                setPrompt(promptInput.getText());
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

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < options.size(); i++) {

            JPanel deletableOption = new JPanel();
            deletableOption.setLayout(new BoxLayout(deletableOption, BoxLayout.X_AXIS));

            int optionIndex = i;
            JTextField optionInput = new JTextField(options.get(optionIndex));
            optionInput.setMaximumSize(new Dimension(400, 50));

            optionInput.getDocument().addDocumentListener(new DocumentListener() {
                private void documentListenerHelper() {
                    options.set(optionIndex, optionInput.getText());
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

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                if (options.size() == 1) {
                    return;
                }
                removeOption(optionIndex);
                controller.updateSurveyCreator();
            });


            deletableOption.add(optionInput);
            deletableOption.add(deleteButton);

            optionPanel.add(deletableOption);

        }



        JButton addButton = new JButton("Add Option");
        addButton.addActionListener(e -> {
            addOption("Enter option here");
            controller.updateSurveyCreator();
        });
        optionPanel.add(addButton);


        JCheckBox toggleSingleAnswer = new JCheckBox("Single answer?");

        toggleSingleAnswer.addActionListener(e -> {
            setSingleAnswer(toggleSingleAnswer.isSelected());
        });

        masterPanel.add(promptInput);
        masterPanel.add(optionPanel);
        masterPanel.add(toggleSingleAnswer);

        return masterPanel;
    }

    @Override
    public Question toQuestion() {
        return new MultipleChoiceQuestion(getPrompt(), getOptions(), isSingleAnswer(), isRequired());
    }
}
