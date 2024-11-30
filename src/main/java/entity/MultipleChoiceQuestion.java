package entity;

import java.util.List;

/**
 * A Question with multiple, fixed answer options.
 */
public class MultipleChoiceQuestion extends Question {

    private final List<String> options;


    public MultipleChoiceQuestion(String prompt,
                                  List<String> options,
                                  boolean singleAnswer,
                                  boolean required) {
        super(prompt, required);
        this.options = options;

        if (singleAnswer) {
            this.answerType = AnswerType.SINGLE_CHOICE;
        } else {
            this.answerType = AnswerType.MULTIPLE_CHOICE;
        }
    }

    public List<String> getOptions() {
        return options;
    }

    public String getOption(int idx) {
        try {
            return options.get(idx);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public boolean validateAnswer(Answer answer) {
        if (getAnswerType() == AnswerType.SINGLE_CHOICE) {
            return answer.getUserInput().length <= 1;
        }
        return answer.getUserInput().length <= options.size();
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
