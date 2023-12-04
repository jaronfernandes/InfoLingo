package use_case.transfer_article;

/**
 * Sends data to DAI and presenter. Heart of the program.
 * Javadocs delegated to interface.
 * @author Jaiz Jeeson
 */
public class TransferArticleInteractor implements TransferArticleInputBoundary {
    private final TransferArticleOutputBoundary presenter;

    /**
     * Instantiates TransferArticleInteractor.
     * @param presenter Object that implements TransferArticleOutputBoundary.
     * @author Jaiz Jeeson
     * @author Jaron Fernandes
     */
    public TransferArticleInteractor(TransferArticleOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(TransferArticleInputData inputData) {
        if (inputData.getArticle() != null) {
            TransferArticleOutputData outputData = new TransferArticleOutputData(inputData.getArticle());
            presenter.prepareSuccessView(outputData);
        }
        else {
            presenter.prepareFailView("Could not find article.");
        }
    }
}
