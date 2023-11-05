package use_case.summarization;

public interface SummarizationOutputBoundary {

    void prepareSuccessView(SummarizationOutputData outputData);
    void prepareFailView(String error);
}
