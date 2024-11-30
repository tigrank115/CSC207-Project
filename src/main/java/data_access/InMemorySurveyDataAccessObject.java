package data_access;

import entity.Response;
import entity.Survey;
import use_case.get_survey.GetSurveyDataAccessInterface;
import use_case.make_response.MakeResponseDataAccessInterface;
import use_case.upload_survey.UploadSurveyDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySurveyDataAccessObject implements
        UploadSurveyDataAccessInterface,
        GetSurveyDataAccessInterface,
        MakeResponseDataAccessInterface {

    private Map<String, Survey> surveyCollection;
    private Map<String, List<Response>> responseCollection;
    private Integer nextHash;

    public InMemorySurveyDataAccessObject() {
        this.surveyCollection = new HashMap<>();
        this.responseCollection = new HashMap<>();
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

    @Override
    public void saveResponse(Response response, String surveyId) {
        if (getSurvey(surveyId) == null) {
            throw new RuntimeException("Survey with id " + surveyId + " not found");
        }

        if (!responseCollection.containsKey(surveyId)) {
            responseCollection.put(surveyId, new ArrayList<>());
        }
        responseCollection.get(surveyId).add(response);
    }
}
