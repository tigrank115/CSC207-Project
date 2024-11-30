package use_case.upload_survey;

public interface UploadSurveyOutputBoundary {
    /**
     * Prepares the success view for the UploadSurvey Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(UploadSurveyOutputData outputData);

    /**
     * Prepares the failure view for the UploadSurvey Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
