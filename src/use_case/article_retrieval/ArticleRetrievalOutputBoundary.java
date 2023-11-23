package use_case.article_retrieval;

public interface ArticleRetrievalOutputBoundary {

    void prepareSuccessView(ArticleRetrievalOutputData outputData);
    void prepareFailView(String error);
}
