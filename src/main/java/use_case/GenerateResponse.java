package use_case;

import entity.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenerateResponse {

    // private List<Question> questionList;
    private LinkedList<Question> questionList;
    private List<Answer> answerList;

    public GenerateResponse(Survey survey) {
        // New copy of the Survey's questions
        this.questionList = new LinkedList<Question>(survey.getQuestions());
        this.answerList = new ArrayList<>();
    }

    private void generateNextAnswer(String[] input) {
        Question nextQuestion = questionList.removeFirst();
        Answer answer = new Answer(nextQuestion.getAnswerType(), input);

        /* TODO: Panic if required question is "unanswered" -- in that case,
        *   probably best to handle input through some concrete pipeline. */

        if (!nextQuestion.validateAnswer(answer)) {
            throw new RuntimeException("An Answer is invalid.");
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
