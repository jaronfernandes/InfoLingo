package interface_adapter;
import entity.Article;

import java.util.ArrayList;
import java.util.List;

public class HomeState {
    List<Article> articles;
    List<String> headlines;
    String articleRetrievalError;

    public HomeState(List<Article> articles) {
        this.articles = articles;
        this.articleRetrievalError = "";
        this.headlines = new ArrayList<>();
        for(int i = 0; i < articles.size(); i++){
            this.headlines.add(articles.get(i).getHeadline());
        }
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<String> getHeadlines() {
        return headlines;
    }

    public void setHeadlines(List<String> headlines) {
        this.headlines = headlines;
    }

    public String getArticleRetrievalError() {
        return articleRetrievalError;
    }

    public void setArticleRetrievalError(String articleRetrievalError) {
        this.articleRetrievalError = articleRetrievalError;
    }
}
