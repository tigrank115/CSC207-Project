package data_access;

import entity.Survey;
import use_case.get_survey.GetSurveyDataAccessInterface;
import use_case.upload_survey.UploadSurveyDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemorySurveyDataAccessObject implements UploadSurveyDataAccessInterface, GetSurveyDataAccessInterface {

    private Map<String, Survey> surveyCollection;
    private Integer nextHash;

    public InMemorySurveyDataAccessObject() {
        this.surveyCollection = new HashMap<>();
        this.nextHash = 0;
    }

    @Override
    public Survey getSurvey(String id) {
        // Could be null if doesn't exist.
        return this.surveyCollection.get(id);
    }

    @Override
    public String saveSurvey(Survey survey) {
        surveyCollection.put(nextHash.toString(), survey);

        // Return current, increment after
        return (nextHash++).toString();
    }
}
