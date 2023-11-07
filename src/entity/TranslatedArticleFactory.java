package entity;

public class TranslatedArticleFactory implements TranslatedArticleFactoryInterface {
    public TranslatedArticle create(String headline, String content, Source source, String translatedLanguage, String author, String URL) {
        return new TranslatedArticle(headline, content, source, translatedLanguage, author, URL);
    }
}
