package use_case.translation;

public class TranslationOutputData {
    String translatedHeadline;
    String translatedContent;
    String language;

    public TranslationOutputData(String translatedHeadline, String translatedContent, String language) {

        this.translatedHeadline = translatedHeadline;
        this.translatedContent = translatedContent;
        this.language = language;
    }

    public String getTranslatedHeadline() {
        return translatedHeadline;
    }

    public String getTranslatedContent() {
        return translatedContent;
    }
    public String getLanguage() { return language; }
}
