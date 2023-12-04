package use_case.article_retrieval;

public interface ArticleRetrievalOutputBoundary {
    /**
     * Changes parameters in view model's state and calls fires a signal to view indicating that parameters have been changed.
     * @param outputData Contains data to be displayed in new ArticleView.
     */
    void prepareSuccessView(ArticleRetrievalOutputData outputData);

    /**
     * In case of error in use case, tells view to display an appropriate message containing the string provided by firing a signal.
     * @param error Message describing error due to which use case failed.
     */
    void prepareFailView(String error);
}
