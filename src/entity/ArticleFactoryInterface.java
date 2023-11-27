package entity;

public interface ArticleFactoryInterface {
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
