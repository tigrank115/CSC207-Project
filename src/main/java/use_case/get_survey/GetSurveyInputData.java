package use_case.get_survey;

public class GetSurveyInputData {
    private final String requestId;
    private final String username;

    public GetSurveyInputData(String username, String requestId) {
        this.requestId = requestId;
        this.username = username;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getUsername() { return username;}
}

