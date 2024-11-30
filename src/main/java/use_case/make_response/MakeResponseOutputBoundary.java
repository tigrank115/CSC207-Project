package use_case.make_response;

public interface MakeResponseOutputBoundary {
    /**
     * Prepares the success view for the MakeResponse Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(MakeResponseOutputData outputData);

    /**
     * Prepares the failure view for the MakeResponse Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
