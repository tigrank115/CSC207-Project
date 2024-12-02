package interface_adapter.create_survey;

import interface_adapter.ViewModel;

public class CreateSurveyViewModel extends ViewModel<CreateSurveyState> {

    public CreateSurveyViewModel() {
        super("create survey");
        setState(new CreateSurveyState());
    }

}
