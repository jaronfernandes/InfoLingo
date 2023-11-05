package use_case.summarization;

import entity.Article;

import java.util.List;

public class SummarizationInteractor implements SummarizationInputBoundary {
    private SummarizationOutputBoundary presenter;
    private SummarizationDataAccessInterface summarizationDataAccessObject;

    public SummarizationInteractor(SummarizationOutputBoundary outputBoundary,
                                   SummarizationDataAccessInterface articleRetrievalDataAccessObject) {
        this.summarizationDataAccessObject = articleRetrievalDataAccessObject;
        this.presenter = outputBoundary;
    }

    @Override
    public void execute(SummarizationInputData inputData) {
        String summarizedText = summarizationDataAccessObject.summarizeArticle(inputData.getContent());

        // TODO: figure out what the fail view would be
        // TODO: remove this 1 == 0 nonsense (i dont want to activate fail view yet)
        if (1 == 0) {
            presenter.prepareFailView("Failed to retrieve any articles!");
        }
        else {
            SummarizationOutputData outputData = new SummarizationOutputData(summarizedText);
            presenter.prepareSuccessView(outputData);
        }
    }
}
