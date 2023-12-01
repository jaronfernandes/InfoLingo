package use_case.ranking;

import entity.Article;
import entity.Preferences;

import java.util.ArrayList;
import java.util.List;

public class RankingInputData {
    private List<Article> articles;
    private List<String> countries;
    private String date;

    public RankingInputData(List<String> countries, String date, List<Article> articles){
        this.countries = countries;
        this.date = date;
        this.articles = articles;
    }

    public List<String> getCountries(){
        return this.countries;
    }

    public String getDate(){
        return this.date;
    }

    public List<Article> getArticles(){
        return this.articles;
    }
}
