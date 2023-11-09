package use_case.summarization;

import entity.Article;

import java.util.List;

public class SummarizationInteractor implements SummarizationInputBoundary {
    private SummarizationOutputBoundary presenter;
    private SummarizationDataAccessInterface summarizationDataAccessObject;
    private SummarizationOutputBoundary summarizationPresenter;
    private String FAIL_TEXT = "";

    public SummarizationInteractor(SummarizationOutputBoundary outputBoundary,
                                   SummarizationDataAccessInterface articleRetrievalDataAccessObject) {
        this.summarizationDataAccessObject = articleRetrievalDataAccessObject;
        this.summarizationPresenter = outputBoundary;
    }

    @Override
    public void execute(SummarizationInputData inputData) {

    }
}
