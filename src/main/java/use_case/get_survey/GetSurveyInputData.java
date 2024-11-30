package use_case.get_survey;

public class GetSurveyInputData {
    private final String requestId;

    public GetSurveyInputData(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }
}
