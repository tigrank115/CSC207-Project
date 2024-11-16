package entity;

public class Answer {

    private AnswerType answerType;
    private String[] userInput;

    public Answer(AnswerType answerType, String[] userInput) {
        this.answerType = answerType;
        this.userInput = userInput;
    }

    public String[] getUserInput() {
        return userInput;
    }
}
