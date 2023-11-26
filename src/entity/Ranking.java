package entity;

import java.util.ArrayList;

public class Ranking {

    private final ArrayList<Article> articles;
    private final Preferences preferences;

    public Ranking(ArrayList<Article> articles, Preferences preferences) {
        this.articles = articles;
        this.preferences = preferences;
    }

    public String getHeadline() {
        return this.articles.get(0).getHeadline();
    }
}
