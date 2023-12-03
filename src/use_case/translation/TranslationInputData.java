package use_case.translation;

import entity.Article;
import interface_adapter.translation.TranslationController;

public class TranslationInputData {

    private String language;
    private String headline;

    public TranslationInputData(String headline, String language) {
        this.language = language;
        this.headline = headline;
    }

    public String getHeadline() { return headline; }

    public String getLanguage() {
        return language;
    }
}
