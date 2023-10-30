package entity;

public class ArticleFactory implements ArticleFactoryInterface {
    public Article create(String headline, String originalContent, Source source) {
        return new Article(headline, originalContent, source);
    }
}
