package interface_adapter.create_survey;

import entity.Question;
import entity.Survey;
import use_case.upload_survey.UploadSurveyInputBoundary;
import use_case.upload_survey.UploadSurveyInputData;

import java.util.Calendar;
import java.util.List;

public class CreateSurveyController {
    private final UploadSurveyInputBoundary uploadUseCase;
    private final CreateSurveyPresenter presenter;
    public CreateSurveyController(UploadSurveyInputBoundary useCase,
                                  CreateSurveyPresenter presenter) {
        this.uploadUseCase = useCase;
        this.presenter = presenter;
    }

    public void addQuestion(String questionType) {

        try {
            Class<? extends Question> questionClass =
                    (Class<? extends Question>) Class.forName("entity." + questionType);

            presenter.addQuestion(questionClass);
        }
        catch (ClassNotFoundException e) {
            presenter.prepareFailView("Question type is invalid: " + questionType);
        }

    }

    public void removeQuestion(int index) {
        presenter.removeQuestion(index);
    }

    public void setRequired(int index, boolean required) {
        presenter.setRequired(index, required);
    }

    public void updateSurveyCreator() {
        presenter.updateCreatorUI();
    }

    public void switchToMainMenu() {
        presenter.switchToMainMenu();
    }

    public void uploadSurvey(CreateSurveyState state) {
        List<QuestionState> questionStates = state.getQuestionStates();

        // No questions, no survey
        if (questionStates.size() == 0) {
            presenter.prepareFailView("No survey exists");
            return;
        }

        Survey survey = new Survey(state.getSurveyName());
        for (QuestionState questionState : questionStates) {
            survey.addQuestion(questionState.toQuestion());
        }

        // Placeholder.
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 1, 0, 0, 0);
        survey.setCloseDate(calendar.getTime());

        uploadUseCase.execute(new UploadSurveyInputData(survey));
    }
}
