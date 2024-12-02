package interface_adapter.respond;

import interface_adapter.ViewModel;

    /**
     * The ViewModel for the Respond to a survey View.
     */
    public class RespondToASurveyViewModel extends ViewModel<RespondToASurveyState> {

        public static final String TITLE_LABEL = "Respond To A Survey";
        public static final String ID_LABEL = "ID : ";
        public static final String CANCEL_BUTTON_LABEL = "Cancel";
        public static final String OK_BUTTON_LABEL = "OK";

        public RespondToASurveyViewModel() {
            super("respond to a survey");
            setState(new RespondToASurveyState());
        }

    }


