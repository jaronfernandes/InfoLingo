package use_case.article_retrieval;

import entity.Article;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRetrievalDataAccessInterface {
    List<Article> getArticles(String rawSearchTerm);
}
