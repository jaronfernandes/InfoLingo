package interface_adapter.ranking;

import entity.Article;
import entity.Preferences;
import interface_adapter.HomeState;
import use_case.ranking.RankingInputBoundary;
import use_case.ranking.RankingInputData;

import java.util.ArrayList;
import java.util.List;

public class RankingController {
    final RankingInputBoundary rankingInteractor;

    public RankingController(RankingInputBoundary rankingInteractor) {
        this.rankingInteractor = rankingInteractor;
    }

    public void execute(List<String> countries, String date, List<Article> articles){
        RankingInputData rankingInputData = new RankingInputData(countries, date, articles);
        rankingInteractor.execute(rankingInputData);
    }
}
