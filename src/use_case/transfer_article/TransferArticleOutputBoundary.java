package use_case.transfer_article;

/**
 * Implemented by presenter to obey dependency rule.
 * @author Jaiz Jeeson
 * @author Jaron Fernandes
 */
public interface TransferArticleOutputBoundary {
    /**
     * Changes parameters in view model's state and calls fires a signal to view indicating that parameters have been changed.
     * @param outputData Contains data relevant to view model's new state.
     */
    void prepareSuccessView(TransferArticleOutputData outputData);

    /**
     * In case of error in use case, tells view to display an appropriate message containing the string provided by firing a signal.
     * @param error Message describing error due to which use case failed.
     */
    void prepareFailView(String error);
}
