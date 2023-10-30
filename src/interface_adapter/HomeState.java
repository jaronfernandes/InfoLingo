package interface_adapter;
import entity.Article;

import java.util.List;

public class HomeState {
    List<Article> articles;
    List<String> headlines;
    String articleRetrievalError;

    public HomeState(List<Article> articles) {
        this.articles = articles;
        this.articleRetrievalError = "";
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    private List<String> getHeadlines() {
        return headlines;
    }

    public void setHeadlines(List<String> headlines) {
        this.headlines = headlines;
    }

    private String getArticleRetrievalError() {
        return articleRetrievalError;
    }

    public void setArticleRetrievalError(String articleRetrievalError) {
        this.articleRetrievalError = articleRetrievalError;
    }
}
