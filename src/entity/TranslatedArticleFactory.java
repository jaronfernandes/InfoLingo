package entity;

public class TranslatedArticleFactory implements TranslatedArticleFactoryInterface {
    public TranslatedArticle create(String headline, String originalContent, Source source, String translatedLanguage) {
        return new TranslatedArticle(headline, originalContent, source, translatedLanguage);
    }
}
