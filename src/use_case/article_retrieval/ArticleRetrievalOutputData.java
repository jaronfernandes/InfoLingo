package use_case.article_retrieval;

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
}
