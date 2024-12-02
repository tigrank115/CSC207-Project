package use_case.send_confirmation;

public class SendConfirmationOutputData {
    private boolean useCaseFailed;
    private String message;
    public SendConfirmationOutputData(String message, boolean useCaseFailed) {
        this.message = message;
        this.useCaseFailed = useCaseFailed;
    }
}
