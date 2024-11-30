package use_case.get_responses;

import data_access.DBSurveyDataAccessObject;
import data_access.InMemorySurveyDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.SampleDataGenerator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetResponsesInteractorTest {

    public static String SAMPLE_SURVEY_ID = "gDKNMUNCz1ZqWsrymkBM";

    @Test
    public void sampleDBResponseTest() {
        DBSurveyDataAccessObject dao = new DBSurveyDataAccessObject();

        try {
            List<Response> responses = dao.getResponses(SAMPLE_SURVEY_ID);
            assertNotNull(responses);
            assertEquals(1, responses.size());
            assertEquals("Good", responses.get(0).getAnswers().get(0).getUserInput()[0]);
            assertEquals("Everything is peachy.", responses.get(0).getAnswers().get(1).getUserInput()[0]);
        }
        catch (RuntimeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getResponsesSuccessTest() {
        Survey survey = SampleDataGenerator.newSurvey();

        // Recall: this example implements GetResponsesDataAccessInterface.
        InMemorySurveyDataAccessObject responseDAO = new InMemorySurveyDataAccessObject();

        // Store the Survey.
        responseDAO.saveSurvey(survey);

        Response response = SampleDataGenerator.newResponse();

        responseDAO.saveResponse(response, "0");

        GetResponsesInputData inputData = new GetResponsesInputData("0");
        GetResponsesOutputBoundary successPresenter = new GetResponsesOutputBoundary() {
            @Override
            public void prepareSuccessView(GetResponsesOutputData outputData) {
                List<Response> responses = outputData.getResponses();
                assertEquals(1, responses.size());
                assertNotNull(responses.get(0));
                assertEquals("Good", responses.get(0).getAnswers().get(0).getUserInput()[0]);
                assertEquals("Everything is peachy.", responses.get(0).getAnswers().get(1).getUserInput()[0]);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }
        };

        GetResponsesInteractor interactor = new GetResponsesInteractor(responseDAO, successPresenter);
        interactor.execute(inputData);
    }
}
