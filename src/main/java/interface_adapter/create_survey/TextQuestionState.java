package interface_adapter.create_survey;

import entity.Question;
import entity.TextQuestion;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.lang.reflect.Constructor;

public class TextQuestionState extends QuestionState {

    public TextQuestionState() {
        super(TextQuestion.class);
    }

    @Override
    public JPanel createQuestionFrame(CreateSurveyController controller) {
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.X_AXIS));

        JTextField promptInput = new JTextField(getPrompt());
        // promptInput.setSize(200, 20);
        masterPanel.add(promptInput);


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

        return masterPanel;
    }

    @Override
    public Question toQuestion() {
        return new TextQuestion(getPrompt(), isRequired());
    }
}
