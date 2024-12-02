package use_case.upload_survey;

import data_access.InMemorySurveyDataAccessObject;
import entity.Survey;
import org.junit.Test;
import use_case.SampleDataGenerator;

import static org.junit.Assert.*;

public class UploadSurveyInteractorTest {

    @Test
    public void successObtainSurveyIdTest() {


        // TODO: Mimic Lab 5 and create some factories for entity classes.
        Survey survey = SampleDataGenerator.newSurvey();

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