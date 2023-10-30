package entity;

import java.util.List;

public class Category {
    private List<Media> articles;

    public Category(List<Media> articles) {
        this.articles = articles;
    }

    public List<Media> getArticles() {
        return articles;
    }
}
