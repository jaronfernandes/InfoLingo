package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Article implements Media, ArticleInterface {
    private String headline;
    private String originalContent;
    private Source source;
    private List<TranslatedArticle> translatedArticles;

    public Article(String headline, String originalContent, Source source) {
        this.headline = headline;
        this.originalContent = originalContent;
        this.source = source;
        this.translatedArticles = new ArrayList<>();
    }

    public String getHeadline() {
        return headline;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public Source getSource() {
        return source;
    }

    public List<TranslatedArticle> getArticles() {
        return translatedArticles;
    }

    public void addTranslatedArticle(TranslatedArticle article) {
        translatedArticles.add(article);
    }
}