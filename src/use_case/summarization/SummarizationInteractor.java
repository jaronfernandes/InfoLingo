package use_case.summarization;

/**
 * Sends data to DAI and presenter. Heart of the program.
 * Javadocs delegated to interface.
 * @author Jaiz Jeeson
 */
public class SummarizationInteractor implements SummarizationInputBoundary {
    private SummarizationDataAccessInterface summarizationDataAccessObject;
    private SummarizationOutputBoundary summarizationPresenter;
    private String FAIL_TEXT = "";

    /**
     * Instantiates SummarizationInteractor.
     * @param summarizationPresenter Object that implements TransferArticleOutputBoundary.
     * @param articleRetrievalDataAccessObject Object that implements SummarizationDataAccessInterface to summarise content.
     * @author Jaiz Jeeson
     */
    public SummarizationInteractor(SummarizationOutputBoundary summarizationPresenter,
                                   SummarizationDataAccessInterface articleRetrievalDataAccessObject) {
        this.summarizationDataAccessObject = articleRetrievalDataAccessObject;
        this.summarizationPresenter = summarizationPresenter;
    }

    @Override
    public void execute(SummarizationInputData inputData) {
        String summarizedText;
        if (inputData.getLength() != null) {
            summarizedText = summarizationDataAccessObject.summarizeArticle(inputData.getContent(), inputData.getLength());
        } else {
            // Prepare fail view if invalid sentence length.
            summarizationPresenter.prepareFailView("Invalid sentence length.");
            return;
        }

        if (summarizedText.equals(this.FAIL_TEXT)) {
            // Prepare fail view if summary is invalid.
            summarizationPresenter.prepareFailView("Could not summarise the article.");
        }
        else {
            SummarizationOutputData outputData = new SummarizationOutputData(summarizedText);
            summarizationPresenter.prepareSuccessView(outputData);
        }
    }
}
