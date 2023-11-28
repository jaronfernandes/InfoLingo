package use_case.ranking;

import entity.Article;
import entity.Preferences;

import java.util.ArrayList;
import java.util.List;

public class RankingInputData {
    private List<String> countries;
    private String date;

    public RankingInputData(List<String> countries, String date){
        this.countries = countries;
        this.date = date;
    }

    public List<String> getArticles(){
        return this.countries;
    }

    public String getPreferences(){
        return this.date;
    }
}
