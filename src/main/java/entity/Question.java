package entity;

public abstract class Question {

    private final boolean required;
    private String prompt;

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

}
