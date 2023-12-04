package use_case.article_retrieval;

import entity.Article;

import java.util.List;

public class ArticleRetrievalOutputData {
    List<Article> articles;

    public ArticleRetrievalOutputData(List<Article> articles) {
        this.articles = articles;
    }

    /**
     * @return articles The list of articles returned from the DAO.
     * @author Jaron Fernandes
     */
    public List<Article> getArticles() {
        return articles;
    }
}
