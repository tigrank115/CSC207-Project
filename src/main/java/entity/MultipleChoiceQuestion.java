package entity;

import java.util.List;

/**
 * A Question with multiple, fixed answer options.
 */
public class MultipleChoiceQuestion extends Question {

    // TODO: Should probably refactor into a different pattern.
    private final List<String> options;

    // Determines whether only one answer can be selected.
    boolean singleAnswer;

    public MultipleChoiceQuestion(String prompt,
                                  List<String> options,
                                  boolean singleAnswer,
                                  boolean required) {
        super(prompt, required);
        this.options = options;
        this.singleAnswer = singleAnswer;
        this.answerType = AnswerType.MULTIPLE_CHOICE;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getOption(int idx) {
        try {
            return options.get(idx);
        } catch (IndexOutOfBoundsException _) {
            return null;
        }
    }
}
