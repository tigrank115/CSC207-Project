package use_case.make_response;

public class MakeResponseOutputData {
    private final boolean useCaseFailed;

    public MakeResponseOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
