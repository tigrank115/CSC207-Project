package use_case.upload_survey;

import data_access.InMemorySurveyDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.Test;
import use_case.login.*;

import java.util.Arrays;
import java.util.Calendar;

import static org.junit.Assert.*;

public class UploadSurveyInteractorTest {

    @Test
    public void successObtainSurveyIdTest() {


        // TODO: Mimic Lab 5 and create some factories for entity classes.
        // TODO: This example Survey is everywhere, refactor somewhere.
        Survey survey = new Survey("Mood Survey");
        survey.addQuestion(new MultipleChoiceQuestion(
                "How are you?",
                Arrays.asList("Good", "So-so", "Bad"),
                false,
                true
        ));
        survey.addQuestion(new TextQuestion(
                "Describe yourself.",
                true
        ));
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 1, 0, 0, 0);
        survey.setCloseDate(calendar.getTime());

        UploadSurveyInputData inputData = new UploadSurveyInputData(survey);
        UploadSurveyDataAccessInterface surveyRepository = new InMemorySurveyDataAccessObject();

        // Create success presenter that accounts for whether we get an ID in return
        UploadSurveyOutputBoundary successPresenter = new UploadSurveyOutputBoundary() {
            @Override
            public void prepareSuccessView(UploadSurveyOutputData outputData) {
                assertEquals("0", outputData.getSurveyRemoteId());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        UploadSurveyInteractor interactor = new UploadSurveyInteractor(surveyRepository, successPresenter);
        interactor.execute(inputData);
    }
}