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

    /**
     * A translated Article entity
     * @param headline String of the headline
     * @param content String of the content
     * @param source Source object of the source
     * @param translatedLanguage String of the language translated to.
     * @param author String of the author
     * @param URL String of the URL
     * @param country String of the country
     * @param publishedAt String of the publishedAt
     */
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
