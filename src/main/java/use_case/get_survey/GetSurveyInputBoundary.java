package use_case.get_survey;

import use_case.upload_survey.UploadSurveyInputData;

public interface GetSurveyInputBoundary {
    /**
     * Executes the GetSurvey use case.
     * @param inputData the input data for the Survey.
     */
    void execute(GetSurveyInputData inputData);
}
