package interface_adapter;

public class ArticleState {
    private String summarisedContent;
    private String summarisationError;

    private String headline = "";
    private String originalContent = "";
    private String translatedHeadline;
    private String translatedContent;
    private String translatedLanguage;

    public void setTranslatedHeadline(String translatedHeadline) {
        this.translatedHeadline = translatedHeadline;
    }

    public void setTranslatedContent(String translatedContent) {
        this.translatedContent = translatedContent;
    }

    public String getTranslatedHeadline() {
        return translatedHeadline;
    }

    public String getTranslatedContent() {
        return translatedContent;
    }

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

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getHeadline() {
        return headline;
    }

    public void setTranslatedLanguage(String translatedLanguage) {
        this.translatedLanguage = translatedLanguage;
    }

    public String getTranslatedLanguage() {
        return translatedLanguage;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public String getOriginalContent() {
        return originalContent;
    }
}
