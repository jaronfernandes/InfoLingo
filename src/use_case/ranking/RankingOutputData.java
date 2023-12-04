package use_case.ranking;

import entity.Article;
import entity.Preferences;
import entity.Ranking;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RankingOutputData {

    private List<Article> ranking;
    private Preferences preferences;

    public RankingOutputData(Ranking ranking) {
        this.ranking = ranking.getAricles();
        this.preferences = ranking.getPreferences();
    }

    /**
     * @return ranking The new Ranking of Articles from retrieved list with entered preferences
     * @author Dominic Le
     */

    public List<Article> getRanking() {
        return ranking;
    }

    public Preferences getPreferences() {
        return preferences;
    }

}
