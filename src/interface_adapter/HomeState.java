package interface_adapter;
import entity.Article;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HomeState {
    List<Article> articles;
    List<String> headlines;
    DefaultListModel<String> headlinesModel;
    String articleRetrievalError;

    public HomeState(List<Article> articles) {
        this.articles = articles;
        this.articleRetrievalError = null;
        this.headlines = new ArrayList<>();
        for(int i = 0; i < articles.size(); i++){
            this.headlines.add(articles.get(i).getHeadline());
        }
        this.headlinesModel = new DefaultListModel<String>();
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

    public void setHeadlinesModel(DefaultListModel<String> headlinesModel) {
        this.headlinesModel = headlinesModel;
    }

    public DefaultListModel<String> getHeadlinesModel() {
        return headlinesModel;
    }

    public Article getArticleByHeadline(String headline) {
        System.out.println(headline);
        for (Article article:
             articles) {
            System.out.println(article.getHeadline());
            if (article.getHeadline().equals(headline)) {
                return article;
            }
        }


        throw new RuntimeException("Unexpected error - couldn't find article with headline.");
    }
}
