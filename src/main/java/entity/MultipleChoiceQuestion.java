package entity;

import java.util.List;

/**
 * A Question with multiple, fixed answer options.
 */
public class MultipleChoiceQuestion extends Question {



    public MultipleChoiceQuestion(String prompt,
                                  List<String> options,
                                  boolean singleAnswer,
                                  boolean required) {
        super(prompt, required, options);

        if (singleAnswer) {
            this.answerType = AnswerType.SINGLE_CHOICE;
        } else {
            this.answerType = AnswerType.MULTIPLE_CHOICE;
        }
    }

    public String getOption(int idx) {
        try {
            return this.getOptions().get(idx);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public boolean validateAnswer(Answer answer) {
        if (getAnswerType() == AnswerType.SINGLE_CHOICE) {
            return answer.getUserInput().length <= 1;
        }
        return answer.getUserInput().length <= this.getOptions().size();
    }

    @Override
    public boolean answerIsEmpty(Answer answer) {
        for (String choice : answer.getUserInput()) {
            if (!choice.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
