package use_case.send_confirmation;

public interface SendConfirmationOutputBoundary {
    void prepareSuccessView(SendConfirmationOutputData outputData);
    void prepareFailView(String errorMessage);
}
