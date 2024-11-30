package entity;

public abstract class Question {

    private final boolean required;
    private final String prompt;

    protected AnswerType answerType;

    public Question(String prompt, boolean required) {
        this.required = required;
        this.prompt = prompt;
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

    // We assume the Answer's AnswerType matches that of the question.
    public abstract boolean validateAnswer(Answer answer);
    public abstract boolean answerIsEmpty(Answer answer);
}
