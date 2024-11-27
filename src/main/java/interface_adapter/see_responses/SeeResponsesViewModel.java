package interface_adapter.see_responses;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

public class SeeResponsesViewModel {

    public static final String TITLE_LABEL = "See Responses View";
    public static final String EXIT_LABEL = "Exit";

    public SeeResponsesViewModel() {
        super("see responses");
        setState(new SeeResponsesState());
    }
}
