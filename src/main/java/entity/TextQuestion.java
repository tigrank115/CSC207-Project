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

    @Override
    public boolean validateAnswer(Answer answer) {
        return answer.getUserInput().length == 1;
    }

    @Override
    public boolean answerIsEmpty(Answer answer) {
        return answer.getUserInput()[0].isEmpty();
    }
}
