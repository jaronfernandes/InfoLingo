package use_case.transfer_article;

/**
 * Implemented by Interactor to obey dependency rule.
 * @author Jaiz Jeeson
 * */
public interface TransferArticleInputBoundary {
    /**
     * Breaks down and manipulates ArticleObject provided in inputData if necessary before packaging into OutputData and relaying it to TransferArticleOutputBoundary.
     * @param inputData Contains data about Article to be displayed.
     * @author Jaiz Jeeson
     */
    void execute(TransferArticleInputData inputData);
}
