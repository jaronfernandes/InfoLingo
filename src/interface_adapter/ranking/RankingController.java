package interface_adapter.ranking;

import entity.Article;
import entity.Preferences;
import use_case.ranking.RankingInputBoundary;
import use_case.ranking.RankingInputData;

import java.util.ArrayList;

public class RankingController {
    final RankingInputBoundary rankingInteractor;

    public RankingController(RankingInputBoundary rankingInteractor) {
        this.rankingInteractor = rankingInteractor;
    }

    public void execute(ArrayList<Article> articles, Preferences preferences){
        RankingInputData rankingInputData = new RankingInputData(articles, preferences);
        rankingInteractor.execute(rankingInputData);
    }
}
