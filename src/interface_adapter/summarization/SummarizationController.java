package interface_adapter.summarization;

import use_case.summarization.SummarizationInputBoundary;
import use_case.summarization.SummarizationInputData;

public class SummarizationController {
    final SummarizationInputBoundary summarizationInteractor;


    public SummarizationController(SummarizationInputBoundary articleRetrievalInteractor) {
        this.summarizationInteractor = articleRetrievalInteractor;
    }

    public void execute(String content, Integer length) {
        String cleanContent = cleanContent(content);
        SummarizationInputData articleRetrievalInputData = new SummarizationInputData(cleanContent, length);
        summarizationInteractor.execute(articleRetrievalInputData);
    }

    private String cleanContent(String dirtyContent) {
        // Replace all whitespace character except space with space.
        String cleanContent = dirtyContent.replaceAll("[\\t\\n\\r\\f\\v]", " ");
        cleanContent = cleanContent.strip();
        System.out.println(cleanContent);
        return cleanContent;
    }
}
