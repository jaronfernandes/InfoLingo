package use_case.translation;

public class TranslationOutputData {
    String translatedHeadline;
    String translatedContent;

    public TranslationOutputData(String translatedHeadline, String translatedContent) {

        this.translatedHeadline = translatedHeadline;
        this.translatedContent = translatedContent;
    }

    public String getTranslatedHeadline() {
        return translatedHeadline;
    }

    public String getTranslatedContent() {
        return translatedContent;
    }
}
