package use_case.summarization;

public class SummarizationInputData {

    private String content;

    public SummarizationInputData(String query) {
        this.content = query;
    }

    public String getContent() {
        return content;
    }

}
