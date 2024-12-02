package use_case.send_confirmation;

public class SendConfirmationInteractor implements SendConfirmationInputBoundary{


    private final SendConfirmationDataAccessInterface confirmationDAO;
    private final SendConfirmationOutputBoundary confirmationPresenter;

    public SendConfirmationInteractor(SendConfirmationDataAccessInterface confirmationDAO,
                                      SendConfirmationOutputBoundary confirmationPresenter) {
        this.confirmationDAO = confirmationDAO;
        this.confirmationPresenter = confirmationPresenter;
    }

    @Override
    public void execute(SendConfirmationInputData inputData) {

        String messageBody = String.format("Hello,\n" +
                "Thank you for your response to %s. It has been recorded.\n\n" +
                "Regards,\n" +
                "QuikSurvey Team", inputData.getSurvey().getName());

        try {
            confirmationDAO.sendConfirmationEmail(inputData.getUser(), inputData.getSurvey().getName(), messageBody);
            confirmationPresenter.prepareSuccessView(new SendConfirmationOutputData(
                    "A confirmation email has been sent to " + inputData.getUser().getEmailAddress(),
                    false));
        }
        catch (RuntimeException e) {
            confirmationPresenter.prepareFailView("Could not send confirmation email to "
                    + inputData.getUser().getEmailAddress() + ": " + e.getMessage());
        }
    }
}
