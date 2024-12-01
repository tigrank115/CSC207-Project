package interface_adapter.create_survey;

import entity.Question;

import javax.swing.*;

public abstract class QuestionState {
    private String prompt = "Enter text here";
    private boolean required = false;
    private final Class<? extends Question> questionClass;

    public QuestionState(Class<? extends Question> questionClass) {
        this.questionClass = questionClass;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    public String getPrompt() {
        return prompt;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public abstract JPanel createQuestionFrame(CreateSurveyController controller);
    public abstract Question toQuestion();
}
