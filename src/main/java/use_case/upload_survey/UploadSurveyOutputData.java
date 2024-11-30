package use_case.upload_survey;

public class UploadSurveyOutputData {
    private final String surveyRemoteId;
    private final boolean useCaseFailed;

    public UploadSurveyOutputData(String surveyRemoteId, boolean useCaseFailed) {
        this.surveyRemoteId = surveyRemoteId;
        this.useCaseFailed = useCaseFailed;
    }

    public String getSurveyRemoteId() {
        return surveyRemoteId;
    }
}
