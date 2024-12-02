package use_case.make_response;

import data_access.InMemorySurveyDataAccessObject;
import entity.*;
import org.junit.Test;
import use_case.SampleDataGenerator;


import static org.junit.Assert.*;


public class MakeResponseInteractorTest {
    @Test
    public void makeResponseSuccessTest() {
        Survey survey = SampleDataGenerator.newSurvey();

        // Recall: this example implements MakeResponseDataAccessInterface.
        InMemorySurveyDataAccessObject responseDAO = new InMemorySurveyDataAccessObject();

        // Store the Survey.
        responseDAO.saveSurvey(survey);

        // Response-related setup
        Response response = SampleDataGenerator.newResponse();

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
