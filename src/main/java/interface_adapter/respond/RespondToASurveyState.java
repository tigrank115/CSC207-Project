package interface_adapter.respond;

/**
 * The state for the Signup View Model.
 */
public class RespondToASurveyState {

    private String surveyID = "";
    private String surveyIDError;

    public String getUsername() {
        return surveyID;
    }
    public String getUsernameError() {
        return surveyIDError;
    }


}
