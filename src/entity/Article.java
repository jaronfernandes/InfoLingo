package entity;

import java.util.List;

public class Article implements Media {
    String headline;
    Source source;
    String originalContent;
    List<TranslatedArticle> translatedArticles;
}
