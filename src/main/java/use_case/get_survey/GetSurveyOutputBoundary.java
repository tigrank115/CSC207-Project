package use_case.get_survey;

import use_case.upload_survey.UploadSurveyOutputData;

public interface GetSurveyOutputBoundary {
    /**
     * Prepares the success view for the GetSurvey Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GetSurveyOutputData outputData);

    /**
     * Prepares the failure view for the GetSurvey Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
