package interface_adapter.surveyresponse;

import interface_adapter.ViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.signup.SignupState;

public class SurveyResponseViewModel extends ViewModel<SurveyResponseState> {

    public static final String TITLE_LABEL = "Survey Response View";

    public SurveyResponseViewModel() {
        super(TITLE_LABEL);
        setState(new SurveyResponseState());
    }
}
