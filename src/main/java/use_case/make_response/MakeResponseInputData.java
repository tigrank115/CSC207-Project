package use_case.make_response;

import entity.Response;
import entity.Survey;

public class MakeResponseInputData {
    private final Response response;
    private final Survey survey;
    private final String surveyId;

    public MakeResponseInputData(Response response,
                                 Survey survey,
                                 String surveyId) {
        this.response = response;
        this.survey = survey;
        this.surveyId = surveyId;
    }

    public Survey getSurvey() {
        return survey;
    }

    public Response getResponse() {
        return response;
    }

    public String getSurveyId() {
        return surveyId;
    }
}
