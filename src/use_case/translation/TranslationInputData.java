package use_case.translation;

import entity.Article;

public class TranslationInputData {

    private Article article;
    private String language;

    public TranslationInputData(Article article, String language) {
        this.article = article;
        this.language = language;
    }

    public Article getArticle() {
        return article;
    }

    public String getLanguage() {
        return language;
    }
}
