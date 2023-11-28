package use_case.transfer_article;

public interface TransferArticleOutputBoundary {

    void prepareSuccessView(TransferArticleOutputData outputData);
    void prepareFailView(String error);
}
