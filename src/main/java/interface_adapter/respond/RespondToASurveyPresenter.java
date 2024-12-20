package interface_adapter.respond;

import interface_adapter.ViewManagerModel;
import interface_adapter.surveyresponse.SurveyResponseState;
import interface_adapter.surveyresponse.SurveyResponseViewModel;
import use_case.get_survey.GetSurveyOutputBoundary;
import use_case.get_survey.GetSurveyOutputData;

import java.awt.*;

/**
 * The Presenter for the Respond to a survey Use Case.
 */
public class RespondToASurveyPresenter extends Component implements GetSurveyOutputBoundary {

    private final RespondToASurveyViewModel respondtoasurveyViewModel;
    private final SurveyResponseViewModel surveyresponseViewModel;
    private final ViewManagerModel viewManagerModel;

    public RespondToASurveyPresenter(ViewManagerModel viewManagerModel,
                           SurveyResponseViewModel surveyresponseViewModel,
                                     RespondToASurveyViewModel respondtoasurveyViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.surveyresponseViewModel = surveyresponseViewModel;
        this.respondtoasurveyViewModel = respondtoasurveyViewModel;
    }

    public void prepareSuccessView(GetSurveyOutputData response) {
        // On success, switch to the survey response view.
        final SurveyResponseState surveyresponseState = surveyresponseViewModel.getState();
        this.surveyresponseViewModel.setState(surveyresponseState);
        surveyresponseViewModel.firePropertyChanged();

        viewManagerModel.setState(surveyresponseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        final RespondToASurveyState respondtoasurveyState = respondtoasurveyViewModel.getState();
        respondtoasurveyState.setSurveyIDError(error);
        respondtoasurveyViewModel.firePropertyChanged();
    }

    @Override
    public void switchToResponseView(String username, GetSurveyOutputData getSurveyOutputData) {
        final SurveyResponseState surveyResponseState = surveyresponseViewModel.getState();
        surveyResponseState.setEmailAddress(username);
        surveyResponseState.setSurvey(getSurveyOutputData.getFetchedSurvey());
        this.surveyresponseViewModel.setState(surveyResponseState);
        this.surveyresponseViewModel.firePropertyChanged();

        viewManagerModel.setState(surveyresponseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
