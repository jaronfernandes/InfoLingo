package entity;

import java.util.List;

public class Category {
    private List<ArticleInterface> articles;

    public Category(List<ArticleInterface> articles) {
        this.articles = articles;
    }

    public List<ArticleInterface> getArticles() {
        return articles;
    }
}
