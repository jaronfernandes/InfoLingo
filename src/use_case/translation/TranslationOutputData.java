package use_case.translation;

public class TranslationOutputData {
    String translatedText;

    public TranslationOutputData(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }
}
