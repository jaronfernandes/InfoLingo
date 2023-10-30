package interface_adapter;
import entity.Article;

public class HomeState {
    Article[] articles;
    String articleRetrievalError;

    public HomeState(Article[] articles) {
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
