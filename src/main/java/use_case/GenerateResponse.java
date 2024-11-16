package use_case;

import entity.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenerateResponse {

    // private List<Question> questionList;
    private LinkedList<Question> questionList;
    private List<Answer<?>> answerList;

    public GenerateResponse(Survey survey) {
        // New copy of the Survey's questions
        this.questionList = new LinkedList<Question>(survey.getQuestions());
        this.answerList = new ArrayList<>();
    }

    private void generateNextAnswer(Object input) {
        Question nextQuestion = questionList.removeFirst();
        Answer<?> answer = nextQuestion.getAnswer(input);

        // Case `input`'s type is unrecognized
        if (answer == null) {
            throw new RuntimeException("Tried to generate Answer from unrecognized type");
        }
        answerList.add(answer);
    }

    public Response generateResponse() {
        // NOTE: Builder would possibly make use of this condition.
        if (!questionList.isEmpty()) {
            throw new RuntimeException("Tried to generate Response without exhausting all Questions.");
        }
        return new Response(answerList);
    }

}
