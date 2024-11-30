package use_case.get_responses;


public interface GetResponsesOutputBoundary {
    /**
     * Prepares the success view for the GetResponses Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GetResponsesOutputData outputData);

    /**
     * Prepares the failure view for the GetResponses Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
