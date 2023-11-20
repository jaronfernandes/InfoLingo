package use_case.translation;

import entity.Article;
import entity.TranslatedArticle;

public interface TranslateAPIDataAccessInterface {
    TranslatedArticle translateArticle(Article article);
}
