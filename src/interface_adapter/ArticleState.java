package interface_adapter;

public class ArticleState {
    private String summarisedContent;
    private String summarisationError;

    private String headline = "";
    private String originalContent = "";

    public void setSummarisedContent(String originalContent) {
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

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getHeadline() {
        return headline;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public String getOriginalContent() {
        return originalContent;
    }
}

