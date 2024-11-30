package use_case.get_survey;

import entity.Survey;

public interface GetSurveyDataAccessInterface {

    Survey getSurvey(String id);
}
