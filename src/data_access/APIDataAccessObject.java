package data_access;

import entity.Article;
import use_case.ArticleRetrievalDataAccessInterface;

import java.util.List;

public class APIDataAccessObject implements ArticleRetrievalDataAccessInterface {
    @Override
    public List<Article> getArticles() {
        return null;
    }
}
