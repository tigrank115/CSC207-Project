package use_case.get_responses;

public class GetResponsesInputData {
    private final String surveyId;

    public GetResponsesInputData(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyId() {
        return surveyId;
    }
}
