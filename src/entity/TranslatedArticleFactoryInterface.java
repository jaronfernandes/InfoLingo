package entity;

public interface TranslatedArticleFactoryInterface {
   TranslatedArticle create(String headline, String originalContent, Source source, String translatedLanguage, String author, String URL);
}
