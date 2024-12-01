package interface_adapter.respond;

/**
 * The state for the Signup View Model.
 */
public class RespondToASurveyState {

    private String surveyID = "";
    private String username = "";
    private String surveyIDError;

    public String getUsername() {
        return username;
    }

    public String getSurveyIDError() {
        return surveyIDError;
    }

    public String getSurveyID() {
        return surveyID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSurveyIDError(String surveyIDError) {
        this.surveyIDError = surveyIDError;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }



}
