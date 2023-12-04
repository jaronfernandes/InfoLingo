package use_case.summarization;

/**
 * Input data for summarisation.
 * @author Jaiz Jeeson
 */
public class SummarizationInputData {
    private final String content;
    private Integer length;
    public SummarizationInputData(String content, Integer length) {
        this.content = content;
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public Integer getLength() {
        return length;
    }
}
