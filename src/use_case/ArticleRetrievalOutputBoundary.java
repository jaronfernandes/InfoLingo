package use_case;

public interface ArticleRetrievalOutputBoundary {

    void prepareSuccessView(ArticleRetrievalOutputData outputData);
    void prepareFailView(String error);
}
