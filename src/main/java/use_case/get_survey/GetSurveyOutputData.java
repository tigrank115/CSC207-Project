package use_case.get_survey;

import entity.Survey;

public class GetSurveyOutputData {
    private final Survey fetchedSurvey;

    public GetSurveyOutputData(Survey fetchedSurvey) {
        this.fetchedSurvey = fetchedSurvey;
    }

    public Survey getFetchedSurvey() {
        return fetchedSurvey;
    }
}
