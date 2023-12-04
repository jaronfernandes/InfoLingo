package interface_adapter.summarization;

import use_case.summarization.SummarizationInputBoundary;
import use_case.summarization.SummarizationInputData;

public class SummarizationController {
    final SummarizationInputBoundary summarizationInteractor;

    public SummarizationController(SummarizationInputBoundary articleRetrievalInteractor) {
        this.summarizationInteractor = articleRetrievalInteractor;
    }


    /**
     * Method that packages and cleans data from ArticleView for data processing after passing to interactor.
     * @param content Content of article to be summarised extracted from a text area in ArticleView.
     * @param length Number of words to which summary is restricted extracted from slider in ArticleView.
     */
    public void execute(String content, Integer length) {
        String cleanContent = cleanContent(content);
        SummarizationInputData articleRetrievalInputData = new SummarizationInputData(cleanContent, length);
        summarizationInteractor.execute(articleRetrievalInputData);
    }

    /**
     * Method that removes problematic whitespace characters that are not allowed in an HTTP request body such as \n.
     * @param dirtyContent Unprocessed article content.
     * @return Processed article content.
     */
    private String cleanContent(String dirtyContent) {
        // Replace all whitespace character except space with space.
        String cleanContent = dirtyContent.replaceAll("[\\t\\n\\r\\f\\v]", " ");
        cleanContent = cleanContent.strip();
        System.out.println(cleanContent);
        return cleanContent;
    }
}
