package entity;

public interface TranslatedArticleFactoryInterface {
   /**
    * A translated Article entity factory
    * @param headline String of the headline
    * @param originalContent String of the content
    * @param source Source object of the source
    * @param translatedLanguage String of the language translated to.
    * @param author String of the author
    * @param URL String of the URL
    * @param country String of the country
    * @param publishedAt String of the publishedAt
    */
   TranslatedArticle create(
           String headline,
           String originalContent,
           Source source,
           String translatedLanguage,
           String author,
           String URL,
           String country,
           String publishedAt
   );
}
