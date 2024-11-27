package use_case.upload_survey;

import entity.Survey;

public interface UploadSurveyDataAccessInterface {
    String saveSurvey(Survey survey);
}
