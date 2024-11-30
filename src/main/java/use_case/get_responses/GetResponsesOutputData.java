package use_case.get_responses;

import entity.Response;

import java.util.List;

public class GetResponsesOutputData {
    private final List<Response> responses;
    private final boolean useCaseFailed;

    public GetResponsesOutputData(List<Response> responses, boolean useCaseFailed) {
        this.responses = responses;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
