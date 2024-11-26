package interface_adapter.respond;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.signup.SignupState;
import use_case.change_password.ChangePasswordOutputData;
import use_case.signup.SignupOutputData;
import view.RespondToASurveyView;

import javax.swing.*;
import java.awt.*;

/**
 * The Presenter for the Respond to a survey Use Case.
 */
public class RespondToASurveyPresenter extends Component {

    private final RespondToASurveyViewModel respondtoasurveyViewModel;
    private final ViewManagerModel viewManagerModel;

    public RespondToASurveyPresenter(ViewManagerModel viewManagerModel,
                           RespondToASurveyViewModel respondtoasurveyViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.respondtoasurveyViewModel = respondtoasurveyViewModel;
    }

    //TODO IMPLEMENT GETSURVEYOUTPUTDATA
    @Override
    public void prepareSuccessView(GetSurveyOutputData response) {
        // On success, switch to the survey response view.
        final RespondToASurveyState respondtoasurveyState = respondtoasurveyViewModel.getState();
        this.respondtoasurveyViewModel.setState(respondtoasurveyState);
        respondtoasurveyViewModel.firePropertyChanged();

        viewManagerModel.setState(respondtoasurveyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final RespondToASurveyState respondtoasurveyState = RespondToASurveyViewModel.getState();
        JOptionPane.showMessageDialog(this, "Invalid ID.");
        respondtoasurveyViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSurveyResponseView() {
        viewManagerModel.setState(respondtoasurveyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
