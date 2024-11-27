package use_case.upload_survey;

import entity.Survey;
import entity.User;

public class UploadSurveyInputData {
    private final Survey survey;

    public UploadSurveyInputData(Survey survey) {
        this.survey = survey;
    }

    public Survey getSurvey() {
        return survey;
    }
}
