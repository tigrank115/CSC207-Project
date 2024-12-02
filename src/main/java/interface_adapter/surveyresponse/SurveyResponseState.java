package interface_adapter.surveyresponse;

import entity.Survey;

public class SurveyResponseState {

    private String surveyID = "";
    private String username = "";
    private Survey survey = null;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }

    public String getSurveyID() {
        return surveyID;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Survey getSurvey() {
        return survey;
    }
}
