package use_case;

import entity.Article;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRetrievalDataAccessInterface {
    List<Article> getArticles();
    Article[] getSampleNews();
}
