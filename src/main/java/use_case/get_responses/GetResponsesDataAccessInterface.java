package use_case.get_responses;

import entity.Response;

import java.util.List;

public interface GetResponsesDataAccessInterface {
    List<Response> getResponses(String surveyId);
}
