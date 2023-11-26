package entity;

import java.util.ArrayList;
import java.util.List;

public class TranslatedArticle implements ArticleInterface {
    private String headline;
    private String content;
    private String translatedLanguage;
    private final String author;
    private final String URL;
    private String country;
    private String publishedAt;
    private Source source;

    public TranslatedArticle(String headline,
                             String content,
                             Source source,
                             String translatedLanguage,
                             String author,
                             String URL,
                             String country,
                             String publishedAt) {
        this.headline = headline;
        this.content = content;
        this.translatedLanguage = translatedLanguage;
        this.source = source;
        this.author = author;
        this.URL = URL;
        this.country = country;
        this.publishedAt = publishedAt;
    }

    @Override
    public String getHeadline() {
        return headline;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public Source getSource() {
        return source;
    }

    public String getLanguage() {
        return translatedLanguage;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getCountry() { return country; }
    @Override
    public String getPublishedAt() { return publishedAt; }

    @Override
    public String getURL() {
        return URL;
    }
}
