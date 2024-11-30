package use_case.get_responses;


public interface GetResponsesInputBoundary {
    /**
     * Executes the GetResponses use case.
     * @param inputData the input data for the Survey.
     */
    void execute(GetResponsesInputData inputData);
}
