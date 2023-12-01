package entity;

import java.util.ArrayList;
import java.util.List;

public class Ranking {

    private final ArrayList<Article> articles;
    private final Preferences preferences;

    public Ranking(ArrayList<Article> articles, Preferences preferences) {
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
