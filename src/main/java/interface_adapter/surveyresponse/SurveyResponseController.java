package interface_adapter.surveyresponse;

import use_case.get_survey.GetSurveyInputData;

public class SurveyResponseController {
    public void execute(String username, String surveyID) {
        final GetSurveyInputData getSurveyInputData = new GetSurveyInputData(
                username, surveyID);




    }
}
