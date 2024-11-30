package use_case.make_response;

import data_access.InMemorySurveyDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static org.junit.Assert.*;


public class MakeResponseInteractorTest {
    @Test
    public void makeResponseTest() {
        Survey survey = new Survey("Mood Survey");
        survey.addQuestion(new MultipleChoiceQuestion(
                "How are you?",
                Arrays.asList("Good", "So-so", "Bad"),
                true,
                true
        ));
        survey.addQuestion(new TextQuestion(
                "Describe yourself.",
                true
        ));
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 1, 0, 0, 0);
        survey.setCloseDate(calendar.getTime());

        // Recall: this example implements MakeResponseDataAccessInterface.
        InMemorySurveyDataAccessObject responseDAO = new InMemorySurveyDataAccessObject();

        // Store the Survey.
        responseDAO.saveSurvey(survey);

        // Response-related setup
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(AnswerType.SINGLE_CHOICE, new String[]{"Good"}));
        answers.add(new Answer(AnswerType.TEXT, new String[]{"Everything is peachy."}));
        Response response = new Response(answers);

        MakeResponseInputData inputData = new MakeResponseInputData(response, survey, "0");
        MakeResponseOutputBoundary successPresenter = new MakeResponseOutputBoundary() {
            @Override
            public void prepareSuccessView(MakeResponseOutputData outputData) {
                System.out.println("MakeResponseInteractorTest: Response validated and saved.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }
        };

        MakeResponseInteractor interactor = new MakeResponseInteractor(responseDAO, successPresenter);
        interactor.execute(inputData);
    }
}
