package use_case.translation;

public interface TranslationOutputBoundary {

    void prepareSuccessView(TranslationOutputData outputData);
    void prepareFailView(String error);
}
