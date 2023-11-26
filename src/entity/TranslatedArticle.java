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

    public TranslatedArticle(String headline, String content, Source source, String translatedLanguage, String author, String URL) {
        this.headline = headline;
        this.content = content;
        this.translatedLanguage = translatedLanguage;
        this.source = source;
        this.author = author;
        this.URL = URL;
    }

    @Override
    public String getHeadline() {
        return headline;
    }

    public String getContent() {
        return content;
    }

    public Source getSource() {
        return source;
    }

    public String getLanguage() {
        return translatedLanguage;
    }

    public String getAuthor() {
        return author;
    }

    public String getURL() {
        return URL;
    }
}
