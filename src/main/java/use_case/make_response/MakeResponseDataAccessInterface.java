package use_case.make_response;

import entity.Response;

public interface MakeResponseDataAccessInterface {
    void saveResponse(Response response, String surveyId);
}
