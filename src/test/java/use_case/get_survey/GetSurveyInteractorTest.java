package use_case.get_survey;

import data_access.DBSurveyDataAccessObject;
import data_access.InMemorySurveyDataAccessObject;
import entity.MultipleChoiceQuestion;
import entity.Survey;
import entity.TextQuestion;
import org.junit.jupiter.api.Test;
import use_case.SampleDataGenerator;

import java.util.Arrays;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class GetSurveyInteractorTest {

    public static String SAMPLE_SURVEY_ID = "gDKNMUNCz1ZqWsrymkBM";

    @Test
    public void getInMemorySurveyTest() {
        Survey survey = SampleDataGenerator.newSurvey();

        InMemorySurveyDataAccessObject surveyRepository = new InMemorySurveyDataAccessObject();
        surveyRepository.saveSurvey(survey);

        GetSurveyOutputBoundary successPresenter = new GetSurveyOutputBoundary() {
            @Override
            public void prepareSuccessView(GetSurveyOutputData outputData) {
                assertNotNull(outputData.getFetchedSurvey());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToResponseView(String username, GetSurveyOutputData getSurveyOutputData) {

            }

        };

        // Want to fetch the survey we just added (the only one in storage)
        GetSurveyInputData inputData = new GetSurveyInputData("randomusername","0");

        GetSurveyInteractor interactor = new GetSurveyInteractor(surveyRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void getDBSurveyTest() {

        // Our database contains one sample Survey already.
        DBSurveyDataAccessObject surveyRepository = new DBSurveyDataAccessObject();

        GetSurveyOutputBoundary successPresenter = new GetSurveyOutputBoundary() {
            @Override
            public void prepareSuccessView(GetSurveyOutputData outputData) {
                assertNotNull(outputData.getFetchedSurvey());
                assertEquals("Mood Survey", outputData.getFetchedSurvey().getName());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToResponseView(String username, GetSurveyOutputData getSurveyOutputData) {

            }
        };

        // Want to fetch the sample survey
        GetSurveyInputData inputData = new GetSurveyInputData("randomusername", SAMPLE_SURVEY_ID);

        GetSurveyInteractor interactor = new GetSurveyInteractor(surveyRepository, successPresenter);
        interactor.execute(inputData);
    }
}
