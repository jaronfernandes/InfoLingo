package use_case.translation;

import entity.Article;
import interface_adapter.translation.TranslationController;

public class TranslationInputData {

    private Article article;
    private String language;
    private String headline;

    public TranslationInputData(Article article, String language) {
        this.language = language;
        this.article = article;
    }

    public TranslationInputData(String headline, String language) {
        this.language = language;
        this.headline = headline;
    }
    public boolean articleExists() {
        return article != null;
    }

    public String getHeadline() { return headline; }

    public Article getArticle() {
        return article;
    }

    public String getLanguage() {
        return language;
    }
}
