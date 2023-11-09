package use_case.summarization;

public class SummarizationInputData {

<<<<<<< Updated upstream
    private String content;
=======
    private final String content;
>>>>>>> Stashed changes

    public SummarizationInputData(String query) {
        this.content = query;
    }

    public String getContent() {
        return content;
    }

}
