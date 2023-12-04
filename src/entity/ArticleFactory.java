package entity;

public class ArticleFactory implements ArticleFactoryInterface {
    /**
     * An unmodified Article entity factory
     * @param headline String of the headline
     * @param originalContent String of the originalContent
     * @param source Source object of the source
     * @param author String of the author
     * @param url String of the url
     * @param country String of the country
     * @param publishedAt String of the publishedAt
     */
    public Article create(String headline,
                          String originalContent,
                          Source source,
                          String author,
                          String url,
                          String country,
                          String publishedAt) {
        return new Article(headline, originalContent, source, author, url, country, publishedAt);
    }
}
