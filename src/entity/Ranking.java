package entity;

import java.util.ArrayList;
import java.util.List;

public class Ranking {

    private final List<Article> articles;
    private final Preferences preferences;

    public Ranking(List<Article> articles, Preferences preferences) {
        this.articles = articles;
        this.preferences = preferences;
    }

    public List<Article> getAricles(){
        return articles;
    }
    public Preferences getPreferences(){
        return preferences;
    }
}
