package entity;

public interface ArticleFactoryInterface {
    Article create(String headline, String originalContent, Source source);
}
