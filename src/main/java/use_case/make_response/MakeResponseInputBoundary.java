package use_case.make_response;

import use_case.make_response.MakeResponseInputData;

public interface MakeResponseInputBoundary {
    /**
     * Executes the MakeResponse use case.
     * @param inputData the input data for the Survey.
     */
    void execute(MakeResponseInputData inputData);
}
