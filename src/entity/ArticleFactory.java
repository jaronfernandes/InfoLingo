package entity;

public class ArticleFactory implements ArticleFactoryInterface {
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
