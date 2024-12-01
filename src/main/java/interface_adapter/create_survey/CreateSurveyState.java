package interface_adapter.create_survey;


import java.util.ArrayList;
import java.util.List;

public class CreateSurveyState {
    private String surveyName = "";
    private List<QuestionState> questionStates = new ArrayList<QuestionState>();

    private String errorMessage = null;


    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void addQuestionState(QuestionState questionState) {
        questionStates.add(questionState);
    }

    public List<QuestionState> getQuestionStates() {
        return questionStates;
    }

    public void setQuestionState(int index, QuestionState questionState) {
        questionStates.set(index, questionState);
    }

    public void removeQuestionState(int index) {
        questionStates.remove(index);
    }


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
