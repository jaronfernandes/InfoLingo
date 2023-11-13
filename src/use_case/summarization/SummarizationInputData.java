package use_case.summarization;

public class SummarizationInputData {

    private final String content;
    public SummarizationInputData(String query) {
        this.content = query;
    }

    public String getContent() {
        return content;
    }

}
