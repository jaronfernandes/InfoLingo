package entity;

public class TranslatedArticleFactory implements TranslatedArticleFactoryInterface {
    public TranslatedArticle create(String headline, String originalContent, Source source, String translatedLanguage, String author, String URL) {
        return new TranslatedArticle(headline, originalContent, source, translatedLanguage, author, URL);
    }
}
