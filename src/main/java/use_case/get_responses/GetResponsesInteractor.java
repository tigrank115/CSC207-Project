package use_case.get_responses;

import entity.Response;
import use_case.make_response.MakeResponseInputData;
import use_case.make_response.MakeResponseOutputData;

import java.util.List;

public class GetResponsesInteractor implements GetResponsesInputBoundary {

    private final GetResponsesDataAccessInterface getResponsesDAO;
    private final GetResponsesOutputBoundary presenter;

    public GetResponsesInteractor(GetResponsesDataAccessInterface getResponsesDAO,
                                  GetResponsesOutputBoundary getResponsesOutputBoundary) {
        this.getResponsesDAO = getResponsesDAO;
        this.presenter = getResponsesOutputBoundary;
    }

    @Override
    public void execute(GetResponsesInputData inputData) {
        try {
            List<Response> responses = getResponsesDAO.getResponses(inputData.getSurveyId());
            presenter.prepareSuccessView(new GetResponsesOutputData(responses, false));
        }
        catch (RuntimeException e) {
            presenter.prepareFailView(e.getMessage());
        }
    }
}
