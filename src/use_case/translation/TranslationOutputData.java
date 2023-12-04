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

    /**
     * @return translatedHeadline The translated news headline
     * @author Jaron Fernandes
     */
    public String getTranslatedHeadline() {
        return translatedHeadline;
    }

    /**
     * @return translatedContent The translated content
     * @author Jaron Fernandes
     */
    public String getTranslatedContent() {
        return translatedContent;
    }
    /**
     * @return language The language
     * @author Jaron Fernandes
     */
    public String getLanguage() { return language; }
}
