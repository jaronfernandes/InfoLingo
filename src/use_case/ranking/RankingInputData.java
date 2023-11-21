package use_case.ranking;

import entity.Article;
import entity.Preferences;

import java.util.ArrayList;

public class RankingInputData {
    private ArrayList<Article> articles;
    private Preferences preferences;

    public RankingInputData(ArrayList<Article> articles, Preferences preferences){
        this.articles = articles;
        this.preferences = preferences;
    }

    public ArrayList<Article> getArticles(){
        return this.articles;
    }

    public Preferences getPreferences(){
        return this.preferences;
    }
}
