package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Article implements ArticleInterface {
    private String headline;
    private String originalContent;
    private Source source;
    private String author;

    private String URL;
    private String country;
    private String publishedAt;
    private List<TranslatedArticle> translatedArticles;

    public Article(String headline,
                   String originalContent,
                   Source source,
                   String author,
                   String url,
                   String country,
                   String publishedAt) {
        this.headline = headline;
        this.originalContent = originalContent;
        this.source = source;
        this.author = author;
        this.URL = url;
        this.country = country;
        this.publishedAt = publishedAt;
        this.translatedArticles = new ArrayList<>();
    }

    public String getCountry() { return country; }

    public String getPublishedAt() { return publishedAt; }

    public String getHeadline() {
        return headline;
    }

    public String getContent() {
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
    public String getAuthor() {
        return author;
    }

    public String getURL() {
        return URL;
    }
}