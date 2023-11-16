package interface_adapter;

public class ArticleState {
    private String summarisedContent = "";
    private String summarisationError = "";

    public void setSummarisedContent(String summarisedContent) {
        this.summarisedContent = summarisedContent;
    }

    public String getSummarisedContent() {
        return summarisedContent;
    }

    public void setSummarisationError(String summarisationError) {
        this.summarisationError = summarisationError;
    }

    public String getSummarisationError() {
        return summarisationError;
    }
}
