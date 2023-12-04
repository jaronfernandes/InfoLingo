package entity;

public class TranslatedArticleFactory implements TranslatedArticleFactoryInterface {
    @Override
    public TranslatedArticle create(
            String headline,
            String originalContent,
            Source source,
            String translatedLanguage,
            String author,
            String URL,
            String country,
            String publishedAt) {
        return new TranslatedArticle(headline, originalContent, source, translatedLanguage, author, URL, country, publishedAt);
    }
}
