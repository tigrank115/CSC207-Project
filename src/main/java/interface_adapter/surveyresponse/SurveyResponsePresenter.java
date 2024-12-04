package interface_adapter.surveyresponse;

import use_case.make_response.MakeResponseOutputBoundary;
import use_case.make_response.MakeResponseOutputData;
import use_case.send_confirmation.SendConfirmationInputBoundary;
import use_case.send_confirmation.SendConfirmationOutputBoundary;
import use_case.send_confirmation.SendConfirmationOutputData;

public class SurveyResponsePresenter implements MakeResponseOutputBoundary, SendConfirmationOutputBoundary {

    public SurveyResponsePresenter() {

    }

    @Override
    public void prepareSuccessView(MakeResponseOutputData outputData) {

    }

    @Override
    public void prepareSuccessView(SendConfirmationOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
