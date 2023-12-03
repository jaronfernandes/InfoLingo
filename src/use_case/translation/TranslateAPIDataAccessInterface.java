package use_case.translation;

import entity.Article;
import entity.TranslatedArticle;

import java.io.IOException;
import java.util.NoSuchElementException;

public interface TranslateAPIDataAccessInterface {
    TranslatedArticle translateArticle(String headline, String language) throws NoSuchElementException, NullPointerException;
}
