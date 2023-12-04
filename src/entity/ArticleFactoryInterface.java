package entity;

public interface ArticleFactoryInterface {
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
    Article create(
            String headline,
            String originalContent,
            Source source,
            String author,
            String url,
            String country,
            String publishedAt
    );
}
