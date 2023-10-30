package use_case;

import entity.Article;

import java.util.List;

public class ArticleRetrievalOutputData {
    List<Article> articles;

    public ArticleRetrievalOutputData(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
