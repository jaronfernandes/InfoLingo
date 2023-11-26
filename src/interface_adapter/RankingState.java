package interface_adapter;

import entity.Article;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RankingState {
    List<Article> rankings;
    List<String> headlines;
    String rankingError;
    public RankingState(List<Article> articles) {
        this.rankings = articles;
        this.rankingError = null;
        this.headlines = new ArrayList<>();
        for(int i = 0; i < articles.size(); i++){
            this.headlines.add(articles.get(i).getHeadline());
        }
    }
    public List<Article> getArticles() {
        return rankings;
    }

    public void setArticles(List<Article> articles) {
        this.rankings = articles;
    }

    public List<String> getHeadlines() {
        return headlines;
    }

    public void setHeadlines(List<String> headlines) {
        this.headlines = headlines;
    }

    public String getRankingError() {
        return rankingError;
    }

    public void setRankingError(String rankingError) {
        this.rankingError = rankingError;
    }

}
