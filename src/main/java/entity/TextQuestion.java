package entity;

/**
 * A Question answered using a text field. This is the most primitive type of
 * Question.
 */
public class TextQuestion extends Question {
    public TextQuestion(String prompt, boolean required) {
        super(prompt, required);
        this.answerType = AnswerType.TEXT;
    }
}
