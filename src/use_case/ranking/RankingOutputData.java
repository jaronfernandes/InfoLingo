package use_case.ranking;

import entity.Article;

import java.util.ArrayList;

public class RankingOutputData {

    ArrayList<Article> ranking;
    public RankingOutputData(ArrayList<Article> ranking) {
        this.ranking = ranking;
    }

    public ArrayList<Article> getRanking() {
        return ranking;
    }

    public void setRanking(ArrayList<Article> ranking) {
        this.ranking = ranking;
    }
}
