package use_case.make_response;


public interface MakeResponseInputBoundary {
    /**
     * Executes the MakeResponse use case.
     * @param inputData the input data for the Survey.
     */
    void execute(MakeResponseInputData inputData);
}
