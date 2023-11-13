package interface_adapter.summarization;

import use_case.summarization.SummarizationInputBoundary;
import use_case.summarization.SummarizationInputData;

public class SummarizationController {
    final SummarizationInputBoundary summarizationInteractor;


    public SummarizationController(SummarizationInputBoundary articleRetrievalInteractor) {
        this.summarizationInteractor = articleRetrievalInteractor;
    }

    public void execute(String content) {
        SummarizationInputData articleRetrievalInputData = new SummarizationInputData(content);
        summarizationInteractor.execute(articleRetrievalInputData);
    }
}
