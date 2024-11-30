package use_case.get_responses;

import use_case.make_response.MakeResponseInputData;

public interface GetResponsesInputBoundary {
    /**
     * Executes the GetResponses use case.
     * @param inputData the input data for the Survey.
     */
    void execute(GetResponsesInputData inputData);
}
