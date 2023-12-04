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

    /**
     * @return headline The news headline
     * @author Jaron Fernandes
     */
    public String getHeadline() { return headline; }

    /**
     * @return langauge The language
     * @author Jaron Fernandes
     */
    public String getLanguage() {
        return language;
    }
}
