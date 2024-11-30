package use_case.upload_survey;

import use_case.login.LoginInputData;

public interface UploadSurveyInputBoundary {
    /**
     * Executes the UploadSurvey use case.
     * @param inputData the input data for the Survey.
     */
    void execute(UploadSurveyInputData inputData);
}
