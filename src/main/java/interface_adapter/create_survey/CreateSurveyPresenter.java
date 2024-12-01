package interface_adapter.create_survey;

import entity.Question;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.upload_survey.UploadSurveyOutputBoundary;
import use_case.upload_survey.UploadSurveyOutputData;

import java.lang.reflect.Constructor;

public class CreateSurveyPresenter implements UploadSurveyOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final CreateSurveyViewModel createSurveyViewModel;
    private final LoggedInViewModel mainMenuViewModel;

    public CreateSurveyPresenter(ViewManagerModel viewManagerModel,
                                 CreateSurveyViewModel createSurveyViewModel,
                                 LoggedInViewModel mainMenuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createSurveyViewModel = createSurveyViewModel;
        this.mainMenuViewModel = mainMenuViewModel;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        CreateSurveyState state = createSurveyViewModel.getState();
        state.setErrorMessage(errorMessage);
        createSurveyViewModel.setState(state);
        createSurveyViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(UploadSurveyOutputData outputData) {

    }


    public void addQuestion(Class<? extends Question> questionClass) {
        CreateSurveyState state = createSurveyViewModel.getState();

        // TextQuestionState questionState = new TextQuestionState();
        QuestionState questionState = QuestionStateFactory.getQuestionState(questionClass);

        state.addQuestionState(questionState);
        state.setErrorMessage(null);
        createSurveyViewModel.setState(state);
        createSurveyViewModel.firePropertyChanged();
    }

    public void removeQuestion(int index) {
        CreateSurveyState createSurveyState = createSurveyViewModel.getState();

        createSurveyState.removeQuestionState(index);
        createSurveyViewModel.setState(createSurveyState);
        createSurveyViewModel.firePropertyChanged();
    }

    public void setRequired(int index, boolean required) {
        CreateSurveyState state = createSurveyViewModel.getState();
        state.getQuestionStates().get(index).setRequired(required);
        createSurveyViewModel.setState(state);
        createSurveyViewModel.firePropertyChanged();
    }

    public void updateCreatorUI() {
        createSurveyViewModel.setState(createSurveyViewModel.getState());
        createSurveyViewModel.firePropertyChanged();
    }

    public void switchToMainMenu() {
        viewManagerModel.setState(mainMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
