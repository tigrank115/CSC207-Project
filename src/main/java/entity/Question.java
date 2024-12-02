package entity;

import java.util.List;

public abstract class Question {

    private final boolean required;
    private final String prompt;
    private final List<String> options;

    protected AnswerType answerType;

    public Question(String prompt, boolean required, List<String> options) {
        this.required = required;
        this.prompt = prompt;
        this.options = options;
    }

    public boolean isRequired() {
        return required;
    }

    public String getPrompt() {
        return prompt;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public List<String> getOptions() {
        return options;
    }

    // We assume the Answer's AnswerType matches that of the question.
    public abstract boolean validateAnswer(Answer answer);
    public abstract boolean answerIsEmpty(Answer answer);
}
