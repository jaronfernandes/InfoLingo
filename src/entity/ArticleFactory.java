package entity;

public class ArticleFactory implements ArticleFactoryInterface {
    public Article create(String headline, String originalContent, Source source, String author, String url) {
        return new Article(headline, originalContent, source, author, url);
    }
}
