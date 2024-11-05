package entity;

public abstract class Question {

    private final boolean required;
    private String prompt;

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

    // This will be used to populate a Response through the interface.
    public abstract Answer<?> generateAnswer();

}
