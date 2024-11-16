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

    public Answer getAnswer(String[] userInput) {
        return new Answer(getAnswerType(), userInput);
    }

}
