package use_case.upload_survey;

import use_case.login.LoginOutputData;

public interface UploadSurveyOutputBoundary {
    /**
     * Prepares the success view for the Login Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(UploadSurveyOutputData outputData);

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
