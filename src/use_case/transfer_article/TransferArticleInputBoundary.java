package use_case.transfer_article;

public interface TransferArticleInputBoundary {
    /**
     * Breaks down and manipulates ArticleObject provided in inputData if necessary before packaging into OutputData and relaying it to TransferArticleOutputBoundary.
     * @param inputData Contains data about Article to be displayed.
     */
    void execute(TransferArticleInputData inputData);
}
