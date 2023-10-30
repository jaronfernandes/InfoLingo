package interface_adapter;
import entity.Article;

import java.util.List;

public class HomeState {
    List<Article> articles;
    String articleRetrievalError;

    public HomeState(List<Article> articles) {
        this.articles = articles;
        this.articleRetrievalError = "";
    }

    public Article[] getArticles() {
        return articles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }

    public String getArticleRetrievalError() {
        return articleRetrievalError;
    }

    public void setArticleRetrievalError(String articleRetrievalError) {
        this.articleRetrievalError = articleRetrievalError;
    }
}
