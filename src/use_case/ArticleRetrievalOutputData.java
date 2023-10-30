package use_case;

import entity.Article;

public class ArticleRetrievalOutputData {
    Article[] articles;

    public ArticleRetrievalOutputData(Article[] articles) {
        this.articles = articles;
    }

    public Article[] getArticles() {
        return articles;
    }

    public void setArticles(Article[] articles) {
        this.articles = articles;
    }
}
