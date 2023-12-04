package use_case.summarization;

/**
 * Output data for summarisation.
 * @author Jaiz Jeeson
 */
public class SummarizationOutputData {
    String summarizedText;

    public SummarizationOutputData(String summarizedText) {
        this.summarizedText = summarizedText;
    }

    public String getSummarizedText() {
        return summarizedText;
    }
}
