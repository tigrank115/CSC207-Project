package interface_adapter.surveyresponse;

import entity.Survey;

public class SurveyResponseState {

    private String surveyID = "";
    private String emailAddress = "";
    private String password = "";
    private Survey survey = null;

    private String errorMessage = null;

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
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

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
